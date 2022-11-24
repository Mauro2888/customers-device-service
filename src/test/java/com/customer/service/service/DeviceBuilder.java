package com.customer.service.service;

import com.customer.service.model.dto.DeviceDto;
import com.customer.service.model.dto.PatchDto;
import com.customer.service.model.entity.Device;
import com.customer.service.model.entity.DeviceStatus;

import java.util.UUID;

/**
 * This class is a builder for generate pre-mock customer and device DTO and ENTITY.
 */
public class DeviceBuilder {
    public static DeviceDto getDeviceDto() {
        return new DeviceDto(UUID.fromString("ee27a333-4091-4ca5-bf45-5c9684789f70"), DeviceStatus.ACTIVE);
    }
    public static DeviceDto getUpdateDeviceDto() {
        return new DeviceDto(UUID.fromString("ee27a333-4091-4ca5-bf45-5c9684789f70"), DeviceStatus.LOST);
    }

   public static Device getDeviceEntity() {
        return new Device(UUID.fromString("ee27a333-4091-4ca5-bf45-5c9684789f70"), DeviceStatus.ACTIVE);
    }

    public static PatchDto.Request.updateStatus getPatchRequestDto() {
        return new PatchDto.Request.updateStatus(DeviceStatus.LOST);
    }
}
