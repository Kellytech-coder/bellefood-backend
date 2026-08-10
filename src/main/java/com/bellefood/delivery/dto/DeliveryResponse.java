package com.bellefood.delivery.dto;

public class DeliveryResponse {

    private String id;
    private String orderId;
    private String address;
    private String landmark;
    private String option;
    private String status;

    public DeliveryResponse() {
    }

    public DeliveryResponse(String id, String orderId, String address,
                            String landmark, String option, String status) {
        this.id = id;
        this.orderId = orderId;
        this.address = address;
        this.landmark = landmark;
        this.option = option;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
