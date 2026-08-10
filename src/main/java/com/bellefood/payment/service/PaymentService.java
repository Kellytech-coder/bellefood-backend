package com.bellefood.payment.service;

import com.bellefood.common.Constants;
import com.bellefood.payment.model.Payment;
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
public class PaymentService {

    private final DatabaseReference databaseReference;

    public PaymentService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference(Constants.NODE_PAYMENTS);
    }

    public CompletableFuture<String> createPayment(Payment payment) {

        CompletableFuture<String> future = new CompletableFuture<>();

        String id = UUID.randomUUID().toString();
        payment.setId(id);
        payment.setCreatedAt(System.currentTimeMillis());

        databaseReference
                .child(id)
                .setValue(payment, (error, reference) -> {

                    if (error != null) {
                        future.completeExceptionally(error.toException());
                    } else {
                        future.complete(id);
                    }
                });

        return future;
    }

    public CompletableFuture<Payment> getPaymentById(String id) {

        CompletableFuture<Payment> future = new CompletableFuture<>();

        databaseReference.child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        Payment payment = snapshot.getValue(Payment.class);

                        future.complete(payment);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        future.completeExceptionally(error.toException());
                    }
                });

        return future;
    }

    public CompletableFuture<List<Payment>> getAllPayments() {

        CompletableFuture<List<Payment>> future = new CompletableFuture<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                List<Payment> payments = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {

                    Payment payment = data.getValue(Payment.class);

                    if (payment != null) {
                        payments.add(payment);
                    }
                }

                future.complete(payments);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                future.completeExceptionally(error.toException());
            }
        });

        return future;
    }
}
