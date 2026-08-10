package com.bellefood.order.service;

import com.bellefood.order.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    private final DatabaseReference databaseReference;

    public OrderService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("orders");
    }

    public CompletableFuture<String> createOrder(Order order) {

        CompletableFuture<String> future = new CompletableFuture<>();

        String id = UUID.randomUUID().toString();
        order.setId(id);

        databaseReference
                .child(id)
                .setValue(order, (error, reference) -> {

                    if (error != null) {
                        future.completeExceptionally(error.toException());
                    } else {
                        future.complete(id);
                    }

                });

        return future;
    }

    public CompletableFuture<Order> getOrderById(String id) {

        CompletableFuture<Order> future = new CompletableFuture<>();

        databaseReference.child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        Order order = snapshot.getValue(Order.class);

                        future.complete(order);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        future.completeExceptionally(error.toException());
                    }
                });

        return future;
    }
}


