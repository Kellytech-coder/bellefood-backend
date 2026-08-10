package com.bellefood.order.controller;

import com.bellefood.order.dto.OrderRequest;
import com.bellefood.order.dto.OrderResponse;
import com.bellefood.order.model.Order;
import com.bellefood.order.model.OrderItem;
import com.bellefood.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<OrderResponse>> createOrder(
            @RequestBody OrderRequest request) {

        return orderService.createOrder(mapToOrder(request))
                .thenApply(orderId -> ResponseEntity.ok(new OrderResponse(orderId)));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Order>> getOrderById(
            @PathVariable String id) {

        return orderService.getOrderById(id)
                .thenApply(order -> {
                    if (order == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(order);
                });
    }

    private Order mapToOrder(OrderRequest request) {

        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerPhone(request.getCustomerPhone());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setDeliveryLandmark(request.getDeliveryLandmark());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setSubtotal(request.getSubtotal());
        order.setDeliveryFee(request.getDeliveryFee());
        order.setTotal(request.getTotal());
        order.setStatus("PENDING");
        order.setOrderTime(System.currentTimeMillis());

        List<OrderItem> items =
                request.getItems() != null ? request.getItems() : new ArrayList<>();

        order.setItems(items);

        return order;
    }
}


