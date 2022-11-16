package com.customer.service.service;

import com.customer.service.exception.CustomerNotFoundException;
import com.customer.service.exception.UserNotAvailableException;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.dto.CustomerDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto getCustomerDevices(String codiceFiscale) {
        return customerMapper.toDto(customerRepository.findCustomerByCodiceFiscale(codiceFiscale)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found")));
    }

    @Transactional
    public String addCustomerUser(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.findCustomerByCodiceFiscale(customer.getCodiceFiscale())
        .ifPresent(user -> {
            throw new UserNotAvailableException("User already taken");
        });
        customerRepository.save(customer);
        return "Success";
    }
}
