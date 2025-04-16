package com.dislicores.api.b2c.app.product.config;

import com.dislicores.api.b2c.app.product.util.UtilProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirestoreConfig {
    @Bean
    Firestore firestore(UtilProperties utilProperties) throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource(utilProperties.getPathJsonFirebase()).getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);
        return FirestoreClient.getFirestore(app);
    }
}
