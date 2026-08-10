package com.bellefood.customer.dto;

public class CustomerRequest {

    private String fullName;
    private String phone;
    private String email;
    private String deliveryAddress;

    public CustomerRequest() {
    }

    public CustomerRequest(String fullName, String phone, String email,
                           String deliveryAddress) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
