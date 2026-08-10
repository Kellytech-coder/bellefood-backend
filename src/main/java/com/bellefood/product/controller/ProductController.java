package com.bellefood.product.controller;

import com.bellefood.product.model.Product;
import com.bellefood.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts()
            throws ExecutionException, InterruptedException {

        List<Product> products = productService.getAllProducts().get();

        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Product>> getProductById(
            @PathVariable String id) {

        return productService.getProductById(id)
                .thenApply(product -> {
                    if (product == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(product);
                });
    }
}