package com.bellefood.delivery.service;

import com.bellefood.common.Constants;
import com.bellefood.delivery.model.DeliveryAddress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class DeliveryService {

    private final DatabaseReference databaseReference;

    public DeliveryService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference(Constants.NODE_DELIVERIES);
    }

    public CompletableFuture<String> createDelivery(DeliveryAddress delivery) {

        CompletableFuture<String> future = new CompletableFuture<>();

        String id = UUID.randomUUID().toString();
        delivery.setId(id);
        delivery.setCreatedAt(System.currentTimeMillis());

        databaseReference
                .child(id)
                .setValue(delivery, (error, reference) -> {

                    if (error != null) {
                        future.completeExceptionally(error.toException());
                    } else {
                        future.complete(id);
                    }
                });

        return future;
    }

    public CompletableFuture<DeliveryAddress> getDeliveryById(String id) {

        CompletableFuture<DeliveryAddress> future = new CompletableFuture<>();

        databaseReference.child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        DeliveryAddress delivery = snapshot.getValue(DeliveryAddress.class);

                        future.complete(delivery);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        future.completeExceptionally(error.toException());
                    }
                });

        return future;
    }

    public CompletableFuture<List<DeliveryAddress>> getAllDeliveries() {

        CompletableFuture<List<DeliveryAddress>> future = new CompletableFuture<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                List<DeliveryAddress> deliveries = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {

                    DeliveryAddress delivery = data.getValue(DeliveryAddress.class);

                    if (delivery != null) {
                        deliveries.add(delivery);
                    }
                }

                future.complete(deliveries);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                future.completeExceptionally(error.toException());
            }
        });

        return future;
    }
}
