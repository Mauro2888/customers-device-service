package com.customer.service.model.dto;

import com.customer.service.model.entity.DeviceStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record DeviceDto(
        UUID id,
        @NotNull
        DeviceStatus status) implements Serializable {
}