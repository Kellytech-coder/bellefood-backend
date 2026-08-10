package com.bellefood.payment.controller;

import com.bellefood.payment.dto.PaymentRequest;
import com.bellefood.payment.dto.PaymentResponse;
import com.bellefood.payment.model.Payment;
import com.bellefood.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments()
            throws ExecutionException, InterruptedException {

        List<Payment> payments = paymentService.getAllPayments().get();

        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Payment>> getPaymentById(
            @PathVariable String id) {

        return paymentService.getPaymentById(id)
                .thenApply(payment -> {
                    if (payment == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(payment);
                });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<PaymentResponse>> createPayment(
            @RequestBody PaymentRequest request) {

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setMethod(request.getMethod());
        payment.setAmount(request.getAmount());
        payment.setStatus("PENDING");

        return paymentService.createPayment(payment)
                .thenApply(paymentId ->
                        ResponseEntity.ok(new PaymentResponse(
                                paymentId,
                                payment.getOrderId(),
                                payment.getMethod(),
                                payment.getAmount(),
                                payment.getStatus()
                        )));
    }
}
