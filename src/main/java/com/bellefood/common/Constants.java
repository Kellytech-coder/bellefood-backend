package com.bellefood.common;

public final class Constants {

    private Constants() {
    }

    // Firebase Database nodes
    public static final String NODE_PRODUCTS = "products";
    public static final String NODE_MENU = "menu";
    public static final String NODE_CART = "cart";
    public static final String NODE_ORDERS = "orders";
    public static final String NODE_CUSTOMERS = "customers";
    public static final String NODE_PAYMENTS = "payments";
    public static final String NODE_DELIVERIES = "deliveries";

    // Order statuses
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_PREPARING = "PREPARING";
    public static final String STATUS_DELIVERING = "DELIVERING";
    public static final String STATUS_DELIVERED = "DELIVERED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    // Payment statuses
    public static final String PAYMENT_PENDING = "PENDING";
    public static final String PAYMENT_PAID = "PAID";
    public static final String PAYMENT_FAILED = "FAILED";

    // Delivery fee (matches NGN flat fee used on the frontend)
    public static final double DELIVERY_FEE = 1000.0;
}

