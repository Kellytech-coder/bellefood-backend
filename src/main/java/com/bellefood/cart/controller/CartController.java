package com.bellefood.cart.controller;

import com.bellefood.cart.model.Cart;
import com.bellefood.cart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getCartItems() throws ExecutionException, InterruptedException {
        return cartService.getAllCartItems().get();
    }

    @PostMapping
    public String addToCart(@RequestBody Cart cart)
            throws ExecutionException, InterruptedException {

        return cartService.addToCart(cart).get();
    }

    @DeleteMapping("/{id}")
    public String deleteCartItem(@PathVariable String id)
            throws ExecutionException, InterruptedException {

        return cartService.deleteCartItem(id).get();
    }
}