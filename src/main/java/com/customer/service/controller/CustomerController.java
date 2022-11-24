package com.customer.service.controller;

import com.customer.service.model.dto.CustomerDto;
import com.customer.service.model.dto.PatchDto;
import com.customer.service.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerDto customer) {
        CustomerDto user = customerService.createCustomerUser(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{codiceFiscale}")
                .buildAndExpand(user.codiceFiscale())
                .toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping(value = "{codiceFiscale}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto findCustomer(@PathVariable String codiceFiscale) {
        return customerService.findCustomerDevices(codiceFiscale);
    }

    @PatchMapping(value = "{codiceFiscale}/indirizzo",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerDto> updateCustomerAddress(@PathVariable String codiceFiscale,
                                                             @RequestBody @Valid PatchDto.Request.updateIndirizzo customerIndirizzoDto) {
        CustomerDto customer = customerService.updateCustomerIndirizzo(codiceFiscale, customerIndirizzoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{codiceFiscale}")
                .buildAndExpand(customer.indirizzo())
                .toUri();
        return ResponseEntity.created(uri).body(customer);
    }
}
