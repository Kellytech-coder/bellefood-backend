package com.bellefood.common;

import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance();
    }

}