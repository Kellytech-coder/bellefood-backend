package com.bellefood.cart.service;

import com.bellefood.cart.model.Cart;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
public class CartService {

    private final DatabaseReference databaseReference;


    public CartService(FirebaseDatabase firebaseDatabase) {

        this.databaseReference =
                firebaseDatabase.getReference("cart");

    }


    public CompletableFuture<List<Cart>> getAllCartItems() {

        CompletableFuture<List<Cart>> future =
                new CompletableFuture<>();


        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        List<Cart> cartItems =
                                new ArrayList<>();


                        for (DataSnapshot data : snapshot.getChildren()) {

                            Cart cart =
                                    data.getValue(Cart.class);

                            if (cart != null) {
                                cartItems.add(cart);
                            }
                        }


                        future.complete(cartItems);
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {

                        future.completeExceptionally(
                                error.toException()
                        );
                    }
                }
        );


        return future;
    }



    public CompletableFuture<String> addToCart(Cart cart) {

        CompletableFuture<String> future =
                new CompletableFuture<>();


        String id =
                UUID.randomUUID().toString();


        cart.setId(id);


        databaseReference
                .child(id)
                .setValue(cart, (error, reference) -> {


                    if (error != null) {

                        future.completeExceptionally(
                                error.toException()
                        );

                    } else {

                        future.complete(
                                "Product added to cart."
                        );
                    }

                });


        return future;
    }




    public CompletableFuture<String> deleteCartItem(String id) {

        CompletableFuture<String> future =
                new CompletableFuture<>();


        databaseReference
                .child(id)
                .removeValue((error, reference) -> {


                    if (error != null) {

                        future.completeExceptionally(
                                error.toException()
                        );

                    } else {

                        future.complete(
                                "Item removed successfully."
                        );
                    }

                });


        return future;
    }

}