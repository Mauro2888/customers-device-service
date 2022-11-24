package com.customer.service.service;

import com.customer.service.exception.CustomerNotFoundException;
import com.customer.service.exception.UserNotAvailableException;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.dto.CustomerDto;
import com.customer.service.model.dto.PatchDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto findCustomerDevices(String codiceFiscale) {
        return customerRepository.findCustomerByCodiceFiscale(codiceFiscale)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    public CustomerDto createCustomerUser(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.findCustomerByCodiceFiscale(customer.getCodiceFiscale())
                .ifPresent(user -> {
                    throw new UserNotAvailableException("User already taken");
                });
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomerIndirizzo(String codiceFiscale, PatchDto.Request.updateIndirizzo indirizzoDto) {
        Customer customer = customerRepository.findCustomerByCodiceFiscale(codiceFiscale)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setIndirizzo(indirizzoDto.getIndirizzo());
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }
}
