package com.customer.service.model;

import java.util.stream.Stream;

public enum DeviceStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    LOST("Lost");

    private final String status;

    DeviceStatus(String status) {
        this.status = status;
    }

    public static DeviceStatus getDeviceStatus(String status){
        return Stream.of(DeviceStatus.values())
                .filter(deviceStatus -> deviceStatus.status.equals(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status"));
    }
}
