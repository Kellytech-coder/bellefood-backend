package com.bellefood.menu.service;

import com.bellefood.menu.model.Menu;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MenuService {

    private final DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReference("menu");

    public CompletableFuture<List<Menu>> getAllMenus() {

        CompletableFuture<List<Menu>> future = new CompletableFuture<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                List<Menu> menus = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {

                    Menu menu = data.getValue(Menu.class);

                    if (menu != null) {
                        menus.add(menu);
                    }
                }

                future.complete(menus);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }

        });

        return future;
    }
}