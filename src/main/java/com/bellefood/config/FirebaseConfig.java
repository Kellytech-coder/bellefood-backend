package com.bellefood.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private static final Logger logger =
            LoggerFactory.getLogger(FirebaseConfig.class);

    private static final String DATABASE_URL =
            "https://bellefood-02-default-rtdb.firebaseio.com";


    @PostConstruct
    public void initializeFirebase() {

        try {

            if (!FirebaseApp.getApps().isEmpty()) {
                logger.info("Firebase already initialized");
                return;
            }


            InputStream serviceAccount =
                    new ClassPathResource(
                            "firebase/serviceAccountKey.json"
                    ).getInputStream();


            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(serviceAccount)
                            )
                            .setDatabaseUrl(DATABASE_URL)
                            .build();


            FirebaseApp.initializeApp(options);

            logger.info("Firebase connected successfully");


        } catch (Exception e) {

            logger.error(
                    "Firebase initialization failed",
                    e
            );

            throw new RuntimeException(e);
        }
    }


    @Bean
    public FirebaseDatabase firebaseDatabase() {

        return FirebaseDatabase.getInstance();

    }
}