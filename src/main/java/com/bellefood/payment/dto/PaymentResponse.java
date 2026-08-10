package com.bellefood.payment.dto;

public class PaymentResponse {

    private String id;
    private String orderId;
    private String method;
    private Double amount;
    private String status;

    public PaymentResponse() {
    }

    public PaymentResponse(String id, String orderId, String method,
                           Double amount, String status) {
        this.id = id;
        this.orderId = orderId;
        this.method = method;
        this.amount = amount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
