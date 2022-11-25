package com.customer.service.mapper;

import com.customer.service.model.dto.customer.CustomerDto;
import com.customer.service.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {
}