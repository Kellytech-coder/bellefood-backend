package com.bellefood.payment.dto;

public class PaymentRequest {

    private String orderId;
    private String method;
    private Double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String orderId, String method, Double amount) {
        this.orderId = orderId;
        this.method = method;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
