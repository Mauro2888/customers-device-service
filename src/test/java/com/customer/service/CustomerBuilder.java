package com.customer.service;

import com.customer.service.model.dto.customer.CustomerDto;
import com.customer.service.model.dto.device.DeviceDto;
import com.customer.service.model.dto.common.PatchDto;
import com.customer.service.model.entity.Customer;
import com.customer.service.model.entity.Device;
import com.customer.service.model.entity.DeviceStatus;

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
                "Via Roma 1", List.of(new DeviceDto(UUID.randomUUID(), DeviceStatus.ACTIVE)));
    }


    public static CustomerDto getCustomerUpdatedDto() {
        return new CustomerDto(
                "Mario",
                "Rossi",
                "MRARSS80A01H501L",
                "Via Cagliari 3", List.of(new DeviceDto(UUID.fromString("ec55c62d-69a1-4367-847e-a2d12de88527"), DeviceStatus.ACTIVE)));
    }

    public static CustomerDto getCustomerDtoWithManyDevices() {

        DeviceDto deviceActive = new DeviceDto(UUID.randomUUID(),DeviceStatus.ACTIVE);
        DeviceDto deviceInactive = new DeviceDto(UUID.randomUUID(),DeviceStatus.INACTIVE);
        DeviceDto deviceLost = new DeviceDto(UUID.randomUUID(),DeviceStatus.LOST);

        List<DeviceDto> devices = List.of(deviceActive, deviceInactive, deviceLost);
        return new CustomerDto(
                "Mario",
                "Rossi",
                "MRARSS80A01H501L",
                "Via Roma 1",
                devices);
    }

    public static Customer getCustomerEntity() {
        return new Customer(
                "Mario",
                "Rossi",
                "MRARSS80A01H501L",
                "Via Roma 1", List.of(new Device(UUID.randomUUID(), DeviceStatus.ACTIVE)));
    }

    public static PatchDto.Request.updateIndirizzo getPatchRequestDto() {
        return new PatchDto.Request.updateIndirizzo("Via Cagliari 3");
    }

    public static CustomerDto getCustomerDtoWithWrongCodiceFiscale() {
        return new CustomerDto(
                "Luca",
                "Boi",
                "asdffg",
                "Via Cagliari 1", List.of(new DeviceDto(UUID.randomUUID(), DeviceStatus.ACTIVE)));
    }
}