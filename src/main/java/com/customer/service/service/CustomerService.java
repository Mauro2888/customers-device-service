package com.customer.service.service;

import com.customer.service.exception.CustomerNotFoundException;
import com.customer.service.exception.UserNotAvailableException;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.dto.customer.CustomerDto;
import com.customer.service.model.dto.common.PatchDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for managing customers.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * Find customer with  associated devices
     * @param codiceFiscale the customer's id
     * @return the customer dto
     */
    public CustomerDto findCustomerDevices(String codiceFiscale) {
        return customerRepository.findCustomerByCodiceFiscale(codiceFiscale)
                .map(customerMapper::toDto)
                .orElseThrow(() -> {
                    LOG.error("Customer not found {}", codiceFiscale);
                    return new CustomerNotFoundException("Customer not found");
                });
    }

    /**
     * Create a new customer with associated devices
     * @param customerDto the customer dto
     * @return the customer dto
     */
    public CustomerDto createCustomerUser(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.findCustomerByCodiceFiscale(customer.getCodiceFiscale())
                .ifPresent(user -> {
                    LOG.error("User already exists {}", customer.getCodiceFiscale());
                    throw new UserNotAvailableException("User already taken");
                });
        return customerMapper.toDto(customerRepository.save(customer));
    }

    /**
     * Update customer's address
     * @param codiceFiscale the customer's id
     * @param indirizzoDto the customer's address to update
     * @return the updated customer dto
     */
    public CustomerDto updateCustomerIndirizzo(String codiceFiscale, PatchDto.Request.updateIndirizzo indirizzoDto) {
        Customer customer = customerRepository.findCustomerByCodiceFiscale(codiceFiscale)
                .orElseThrow(() -> {
                    LOG.error("Customer not found {}", codiceFiscale);
                    return new CustomerNotFoundException("Customer not found");
                });
        customer.setIndirizzo(indirizzoDto.getIndirizzo());
        customerRepository.save(customer);
        LOG.info("Customer indirizzo updated {}", codiceFiscale);
        return customerMapper.toDto(customer);
    }
}
