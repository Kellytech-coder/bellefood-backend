package com.bellefood.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI belleFoodOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BelleFood API")
                        .description("REST API for the BelleFood Food Delivery Application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("BelleFood")
                                .email("support@bellefood.com"))
                        .license(new License()
                                .name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("BelleFood Documentation"));
    }
}