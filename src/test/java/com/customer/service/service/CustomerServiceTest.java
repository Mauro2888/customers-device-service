package com.customer.service.service;

import com.customer.service.CustomerBuilder;
import com.customer.service.exception.UserNotAvailableException;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.dto.CustomerDto;
import com.customer.service.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerService underTestService;
    @MockBean
    private CustomerRepository customerRepositoryUnderTest;

    private CustomerMapper customerMapperUnderTest = Mappers.getMapper(CustomerMapper.class);

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
        underTestService = new CustomerService(
                customerRepositoryUnderTest,
                customerMapperUnderTest);
    }

    @Test
    void isGettingCustomerDevices() {

     Mockito.when(customerRepositoryUnderTest.findCustomerByCodiceFiscale(CustomerBuilder.getCustomerEntity().getCodiceFiscale()))
             .thenReturn(Optional.of(CustomerBuilder.getCustomerEntity()));

        CustomerDto customerDevices = underTestService.getCustomerDevices(CustomerBuilder.getCustomerDto().codiceFiscale());
        Assert.notNull(customerDevices, "Customer not found");
        Assertions.assertThat(customerDevices.name()).isNotEmpty();
        Assertions.assertThat(customerDevices.name()).isEqualTo(CustomerBuilder.getCustomerDto().name());
    }

    @Test
    void isAddingCustomerUser() {
        Mockito.when(customerRepositoryUnderTest.save(CustomerBuilder.getCustomerEntity()))
                .thenReturn(CustomerBuilder.getCustomerEntity());
        String user = underTestService.addCustomerUser(CustomerBuilder.getCustomerDto());
        Assertions.assertThat(user).isEqualTo("Success");
    }

    @Test
    void isAlreadyPresent() {
        Mockito.when(customerRepositoryUnderTest.findCustomerByCodiceFiscale(CustomerBuilder.getCustomerEntity().getCodiceFiscale()))
                .thenReturn(Optional.of(CustomerBuilder.getCustomerEntity()));

        UserNotAvailableException thrown = assertThrows(UserNotAvailableException.class,
                () -> underTestService.addCustomerUser(CustomerBuilder.getCustomerDto())

        );
        assertTrue(thrown.getMessage().contains("User already taken"));
    }
}