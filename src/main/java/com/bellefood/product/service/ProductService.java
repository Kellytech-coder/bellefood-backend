package com.bellefood.product.service;

import com.bellefood.product.model.Product;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    private final DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReference("products");

    public CompletableFuture<List<Product>> getAllProducts() {

        CompletableFuture<List<Product>> future = new CompletableFuture<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                System.out.println("Products exists: " + snapshot.exists());
                System.out.println("Children count: " + snapshot.getChildrenCount());

                List<Product> products = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {

                    System.out.println("Key: " + data.getKey());

                    Product product = data.getValue(Product.class);

                    System.out.println(product);

                    if (product != null) {
                        products.add(product);
                    }
                }

                future.complete(products);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                error.toException().printStackTrace();

                future.completeExceptionally(error.toException());
            }
        });

        return future;
    }

    public CompletableFuture<Product> getProductById(String id) {

        CompletableFuture<Product> future = new CompletableFuture<>();

        databaseReference.child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        Product product = snapshot.getValue(Product.class);

                        future.complete(product);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        future.completeExceptionally(error.toException());
                    }
                });

        return future;
    }
}