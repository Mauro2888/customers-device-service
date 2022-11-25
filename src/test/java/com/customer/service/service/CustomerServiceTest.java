package com.customer.service.service;

import com.customer.service.CustomerBuilder;
import com.customer.service.exception.UserNotAvailableException;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.dto.customer.CustomerDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

/**
 * This class test the service customer layer of the application
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerService underTestService;
    @Mock
    private CustomerRepository customerRepositoryUnderTest;

    private static Validator validator;

    private final CustomerMapper customerMapperUnderTest = Mappers.getMapper(CustomerMapper.class);

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
       validator = validatorFactory.getValidator();
        underTestService = new CustomerService(
                customerRepositoryUnderTest,
                customerMapperUnderTest);
    }

    /**
     * This test find use and device and test the GET method
     */
    @Test
    void isGettingCustomerDevicesTest() {

        Mockito.when(customerRepositoryUnderTest.findCustomerByCodiceFiscale(CustomerBuilder.getCustomerEntity().getCodiceFiscale()))
             .thenReturn(Optional.of(CustomerBuilder.getCustomerEntity()));

        CustomerDto customerDevices = underTestService.findCustomerDevices(CustomerBuilder.getCustomerDto().codiceFiscale());
        Assert.notNull(customerDevices, "Customer not found");
        Assertions.assertThat(customerDevices.nome()).isNotEmpty();
        Assertions.assertThat(customerDevices.nome()).isEqualTo(CustomerBuilder.getCustomerDto().nome());
    }

    /**
     * This test validate the creation of a new customer and test the POST method
     */
    @Test
    void isAddingCustomerUserTest() {
        Mockito.when(customerRepositoryUnderTest.save(any(Customer.class)))
                .thenReturn(CustomerBuilder.getCustomerEntity());
        CustomerDto user = underTestService.createCustomerUser(CustomerBuilder.getCustomerDto());
        Assertions.assertThat(user.nome()).isEqualTo("Mario");
    }

    /**
     * This test validate the is updating customer address and PATCH method
     */
    @Test
    void isUpdatingCustomerAddressTest(){
        Mockito.when(customerRepositoryUnderTest.findCustomerByCodiceFiscale(CustomerBuilder.getCustomerEntity().getCodiceFiscale()))
                .thenReturn(Optional.of(CustomerBuilder.getCustomerEntity()));
        Mockito.when(customerRepositoryUnderTest.save(any(Customer.class)))
                .thenReturn(CustomerBuilder.getCustomerEntity());
        CustomerDto customerDto = underTestService.updateCustomerIndirizzo(CustomerBuilder.getCustomerDto().codiceFiscale(),CustomerBuilder.getPatchRequestDto() );
        Assertions.assertThat(customerDto.indirizzo()).isEqualTo("Via Cagliari 3");
    }

    /**
     * This test check if the user is already present.
     * @throws UserNotAvailableException the user not available exception
     */
    @Test
    void isAlreadyPresentTest() {
        Mockito.when(customerRepositoryUnderTest.findCustomerByCodiceFiscale(CustomerBuilder.getCustomerEntity().getCodiceFiscale()))
                .thenReturn(Optional.of(CustomerBuilder.getCustomerEntity()));

        UserNotAvailableException thrown = assertThrows(UserNotAvailableException.class,
                () -> underTestService.createCustomerUser(CustomerBuilder.getCustomerDto())
        );
        assertTrue(thrown.getMessage().contains("User already taken"));
    }

    /**
     * Validation devices test.
     * This test validate the list of device in customer DTO
     * and check if the list contains more than 2 devices
     */
    @Test
    void validationDevicesTest() {
        CustomerDto manyDevices = CustomerBuilder.getCustomerDtoWithManyDevices();
        Set<ConstraintViolation<CustomerDto>> violations = validator.validate(manyDevices);
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }
    @Test
    void validationCodiceFiscaleTest() {
        CustomerDto wrongCodiceFiscale = CustomerBuilder.getCustomerDtoWithWrongCodiceFiscale();
        Set<ConstraintViolation<CustomerDto>> violations = validator.validate(wrongCodiceFiscale);
        String message = violations.stream().map(ConstraintViolation::getMessage).findFirst().get();
        Assertions.assertThat(violations.size()).isEqualTo(1);
        Assertions.assertThat(message).isEqualTo("Codice Fiscale not valid");
    }
}