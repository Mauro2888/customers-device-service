package com.customer.service.repository;

import com.customer.service.CustomerBuilder;
import com.customer.service.model.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepoUnderTest;

    @Test
    void findCustomerByCodiceFiscaleTest() {
        customerRepoUnderTest.save(CustomerBuilder.getCustomerEntity());
        Optional<Customer> marioCFS =
                customerRepoUnderTest.findCustomerByCodiceFiscale("MRARSS80A01H501L");
        boolean exists = marioCFS.isPresent();
        assertThat(exists).isTrue();
    }

    @Test
    void findCustomerByNameTest(){
        customerRepoUnderTest.save(CustomerBuilder.getCustomerEntity());
        Optional<Customer> customerByName = customerRepoUnderTest.findCustomerByName("Mario");
        boolean existsByName = customerByName.isPresent();
        assertThat(existsByName).isTrue();
    }
}