package com.customer.service.controller;

import com.customer.service.model.dto.CustomerDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCustomerUser(@RequestBody @Valid CustomerDto customer) {
        return new ResponseEntity<>(customerService.addCustomerUser(customer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/customer/{codiceFiscale}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto getCustomerUserDevices(@PathVariable String codiceFiscale) {
        return customerService.getCustomerDevices(codiceFiscale);
    }
}
