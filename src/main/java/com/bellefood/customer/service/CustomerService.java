package com.bellefood.customer.service;

import com.bellefood.customer.model.Customer;
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
public class CustomerService {

    private final DatabaseReference databaseReference;

    public CustomerService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("customers");
    }

    public CompletableFuture<String> createCustomer(Customer customer) {

        CompletableFuture<String> future = new CompletableFuture<>();

        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customer.setCreatedAt(System.currentTimeMillis());

        databaseReference
                .child(id)
                .setValue(customer, (error, reference) -> {

                    if (error != null) {
                        future.completeExceptionally(error.toException());
                    } else {
                        future.complete(id);
                    }
                });

        return future;
    }

    public CompletableFuture<Customer> getCustomerById(String id) {

        CompletableFuture<Customer> future = new CompletableFuture<>();

        databaseReference.child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        Customer customer = snapshot.getValue(Customer.class);

                        future.complete(customer);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        future.completeExceptionally(error.toException());
                    }
                });

        return future;
    }

    public CompletableFuture<List<Customer>> getAllCustomers() {

        CompletableFuture<List<Customer>> future = new CompletableFuture<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                List<Customer> customers = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {

                    Customer customer = data.getValue(Customer.class);

                    if (customer != null) {
                        customers.add(customer);
                    }
                }

                future.complete(customers);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                future.completeExceptionally(error.toException());
            }
        });

        return future;
    }
}
