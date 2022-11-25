package com.customer.service.controller;

import com.customer.service.model.dto.customer.CustomerDto;
import com.customer.service.model.dto.common.PatchDto;
import com.customer.service.service.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer Created"),
            @ApiResponse(responseCode = "409", description = "User already taken"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerDto customer) {
        CustomerDto user = customerService.createCustomerUser(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{codiceFiscale}")
                .buildAndExpand(user.codiceFiscale())
                .toUri();
        LOG.info("Customer created: {}",user);
        return ResponseEntity.created(uri).body(user);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Found"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found")
    })
    @GetMapping(value = "{codiceFiscale}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto findCustomer(@PathVariable String codiceFiscale) {
        LOG.info("Search Customer : {}", codiceFiscale);
        return customerService.findCustomerDevices(codiceFiscale);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer Updated"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found")
    })
    @PatchMapping(value = "{codiceFiscale}/indirizzo",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerDto> updateCustomerAddress(@PathVariable String codiceFiscale,
                                                             @RequestBody @Valid PatchDto.Request.updateIndirizzo customerIndirizzoDto) {
        CustomerDto customer = customerService.updateCustomerIndirizzo(codiceFiscale, customerIndirizzoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{codiceFiscale}")
                .buildAndExpand(customer.indirizzo())
                .toUri();
        LOG.info("Customer updated: {}",customer);
        return ResponseEntity.created(uri).body(customer);
    }
}
