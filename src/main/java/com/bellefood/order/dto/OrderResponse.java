package com.bellefood.order.dto;

public class OrderResponse {

    private String id;

    public OrderResponse() {
    }

    public OrderResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


