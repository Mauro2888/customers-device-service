package com.customer.service;

import com.customer.service.model.DeviceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerApplicationTests {

    /**
     * DeviceStatus enum.
     * Simple test for make sure the return from enum class is correct
     */
    @Test
    void getDeviceStatusTest(){
        DeviceStatus active = DeviceStatus.getDeviceStatus("Active");
        DeviceStatus inactive = DeviceStatus.getDeviceStatus("Inactive");
        DeviceStatus lost = DeviceStatus.getDeviceStatus("Lost");

        assertEquals(DeviceStatus.ACTIVE, active);
        assertEquals(DeviceStatus.INACTIVE, inactive);
        assertEquals(DeviceStatus.LOST, lost);
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> DeviceStatus.getDeviceStatus("Wrong"));

        assertEquals("Invalid status", exception.getMessage());

    }

}
