package com.bellefood.customer.controller;

import com.bellefood.customer.dto.CustomerRequest;
import com.bellefood.customer.dto.CustomerResponse;
import com.bellefood.customer.model.Customer;
import com.bellefood.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers()
            throws ExecutionException, InterruptedException {

        List<Customer> customers = customerService.getAllCustomers().get();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerById(
            @PathVariable String id) {

        return customerService.getCustomerById(id)
                .thenApply(customer -> {
                    if (customer == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(customer);
                });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<CustomerResponse>> createCustomer(
            @RequestBody CustomerRequest request) {

        Customer customer = mapToCustomer(request);

        return customerService.createCustomer(customer)
                .thenApply(customerId -> {
                    CustomerResponse response = new CustomerResponse();
                    response.setId(customerId);
                    response.setFullName(customer.getFullName());
                    response.setPhone(customer.getPhone());
                    response.setEmail(customer.getEmail());
                    response.setDeliveryAddress(customer.getDeliveryAddress());
                    response.setCreatedAt(customer.getCreatedAt());
                    return ResponseEntity.ok(response);
                });
    }

    private Customer mapToCustomer(CustomerRequest request) {

        Customer customer = new Customer();
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setDeliveryAddress(request.getDeliveryAddress());

        return customer;
    }
}
