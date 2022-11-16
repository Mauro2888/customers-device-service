package com.customer.service;

import com.customer.service.model.dto.CustomerDto;
import com.customer.service.model.dto.DeviceDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.model.entity.Device;
import com.customer.service.model.entity.DeviceStatus;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Util Builder Class
 * This class is used to create a Customer Entity and a CustomerDto
 */
public class CustomerBuilder {

    public static CustomerDto getCustomerDto() {
        return new CustomerDto(
                "Mario",
                "Rossi",
                "MRARSS80A01H501L",
                "Via Roma 1", List.of(new DeviceDto(UUID.randomUUID(),
                DeviceStatus.ACTIVE)));
    }

    public static Customer getCustomerEntity() {
        return new Customer(
                "Mario",
                "Rossi",
                "MRARSS80A01H501L",
                "Via Roma 1",
                List.of(new Device(UUID.randomUUID(), DeviceStatus.ACTIVE),
                        new Device(UUID.randomUUID(), DeviceStatus.INACTIVE)));
    }
}