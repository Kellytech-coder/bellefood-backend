package com.bellefood.delivery.controller;

import com.bellefood.delivery.dto.DeliveryRequest;
import com.bellefood.delivery.dto.DeliveryResponse;
import com.bellefood.delivery.model.DeliveryAddress;
import com.bellefood.delivery.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAddress>> getAllDeliveries()
            throws ExecutionException, InterruptedException {

        List<DeliveryAddress> deliveries = deliveryService.getAllDeliveries().get();

        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<DeliveryAddress>> getDeliveryById(
            @PathVariable String id) {

        return deliveryService.getDeliveryById(id)
                .thenApply(delivery -> {
                    if (delivery == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(delivery);
                });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<DeliveryResponse>> createDelivery(
            @RequestBody DeliveryRequest request) {

        DeliveryAddress delivery = new DeliveryAddress();
        delivery.setOrderId(request.getOrderId());
        delivery.setAddress(request.getAddress());
        delivery.setLandmark(request.getLandmark());
        delivery.setOption(request.getOption());
        delivery.setStatus("PENDING");

        return deliveryService.createDelivery(delivery)
                .thenApply(deliveryId ->
                        ResponseEntity.ok(new DeliveryResponse(
                                deliveryId,
                                delivery.getOrderId(),
                                delivery.getAddress(),
                                delivery.getLandmark(),
                                delivery.getOption(),
                                delivery.getStatus()
                        )));
    }
}
