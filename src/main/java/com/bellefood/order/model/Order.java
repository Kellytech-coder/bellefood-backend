package com.bellefood.order.model;

import java.util.List;

public class Order {

    private String id;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String deliveryAddress;
    private String deliveryLandmark;
    private String paymentMethod;
    private List<OrderItem> items;
    private Double subtotal;
    private Double deliveryFee;
    private Double total;
    private String status;
    private Long orderTime;

    public Order() {
    }

    public Order(String id, String customerName, String customerPhone,
                 String customerEmail, String deliveryAddress,
                 String deliveryLandmark, String paymentMethod,
                 List<OrderItem> items, Double subtotal,
                 Double deliveryFee, Double total,
                 String status, Long orderTime) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.deliveryAddress = deliveryAddress;
        this.deliveryLandmark = deliveryLandmark;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.total = total;
        this.status = status;
        this.orderTime = orderTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryLandmark() {
        return deliveryLandmark;
    }

    public void setDeliveryLandmark(String deliveryLandmark) {
        this.deliveryLandmark = deliveryLandmark;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }
}

