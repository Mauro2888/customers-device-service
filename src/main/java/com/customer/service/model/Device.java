package com.customer.service.model;

import java.util.UUID;

public record Device(UUID id, DeviceStatus status) {
}
