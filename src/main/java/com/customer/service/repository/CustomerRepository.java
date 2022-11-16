package com.customer.service.repository;

import com.customer.service.model.entity.Customer;
import com.customer.service.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select customer from Customer customer where customer.name = ?1")
    Optional<Customer> findCustomerByName(String name);
    @Query("select customer from Customer customer where customer.codiceFiscale = ?1")
    Optional<Customer>findCustomerByCodiceFiscale(String codiceFiscale);
}
