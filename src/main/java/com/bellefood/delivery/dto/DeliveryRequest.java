package com.bellefood.delivery.dto;

public class DeliveryRequest {

    private String orderId;
    private String address;
    private String landmark;
    private String option;

    public DeliveryRequest() {
    }

    public DeliveryRequest(String orderId, String address,
                           String landmark, String option) {
        this.orderId = orderId;
        this.address = address;
        this.landmark = landmark;
        this.option = option;
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
}
