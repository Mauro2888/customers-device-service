package com.customer.service.controller;

import com.customer.service.CustomUtils;
import com.customer.service.CustomerBuilder;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.dto.CustomerDto;
import com.customer.service.model.dto.DeviceDto;
import com.customer.service.model.dto.PatchDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.model.entity.DeviceStatus;
import com.customer.service.repository.CustomerRepository;
import com.customer.service.service.CustomerService;
import com.customer.service.service.DeviceBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.Validation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService underTestService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/customer/{codiceFiscale}", CustomerBuilder.getCustomerDto().codiceFiscale())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void create() throws Exception {

        Mockito.when(underTestService.createCustomerUser(any(CustomerDto.class)))
                .thenReturn(CustomerBuilder.getCustomerDto());

        mockMvc.perform(post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(CustomerBuilder.getCustomerDto()))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
       .andExpect(status().isCreated());
    }


    @Test
    void update() throws Exception {
        Mockito.when(underTestService.updateCustomerIndirizzo(any(), any(PatchDto.Request
                .updateIndirizzo.class)))
                .thenReturn(CustomerBuilder.getCustomerUpdatedDto());

        mockMvc.perform(patch("/api/v1/customer/{codiceFiscale}/indirizzo",
                CustomerBuilder.getCustomerUpdatedDto().codiceFiscale())
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(CustomerBuilder.getPatchRequestDto()))
        ).andDo(print()).andExpect(MockMvcResultMatchers.content()
                        .string(CustomUtils.asJsonString(CustomerBuilder.getCustomerUpdatedDto())))
                .andExpect(status().isCreated());
    }
}