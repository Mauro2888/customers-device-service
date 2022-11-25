package com.customer.service.service;

import com.customer.service.mapper.DeviceMapper;
import com.customer.service.model.dto.device.DeviceDto;
import com.customer.service.model.entity.Device;
import com.customer.service.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DeviceServiceTest {

    @InjectMocks
    private DeviceService underTestService;
    @Mock
    private DeviceRepository deviceRepositoryUnderTest;

    private final DeviceMapper deviceMapperUnderTest = Mappers.getMapper(DeviceMapper.class);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTestService = new DeviceService(
                deviceRepositoryUnderTest,
                deviceMapperUnderTest);
    }

    @Test
    void isGettingDeviceTest() {
        Mockito.when(deviceRepositoryUnderTest.findById(DeviceBuilder.getDeviceEntity().getId()))
                .thenReturn(Optional.of(DeviceBuilder.getDeviceEntity()));

    DeviceDto deviceDto = underTestService.getDevice(DeviceBuilder.getDeviceDto().id());
    Assertions.assertEquals(deviceDto.id(), DeviceBuilder.getDeviceDto().id());

    }

    @Test
    void isDeleteDeviceTest() {
        Mockito.when(deviceRepositoryUnderTest.findById(DeviceBuilder.getDeviceEntity().getId()))
                .thenReturn(Optional.of(DeviceBuilder.getDeviceEntity()));

        underTestService.deleteDevice(DeviceBuilder.getDeviceDto().id());
        Mockito.verify(deviceRepositoryUnderTest, Mockito.times(1)).deleteById(DeviceBuilder.getDeviceEntity().getId());
    }

    @Test
    void IsUpdatingDeviceStatusTest() {
        Mockito.when(deviceRepositoryUnderTest.findById(DeviceBuilder.getDeviceEntity().getId()))
                .thenReturn(Optional.of(DeviceBuilder.getDeviceEntity()));

        Mockito.when(deviceRepositoryUnderTest.save(any(Device.class)))
                .thenReturn(DeviceBuilder.getDeviceEntity());
        underTestService.updateDeviceStatus(String.valueOf(DeviceBuilder.getDeviceDto().id()), DeviceBuilder.getPatchRequestDto());
        Assertions.assertEquals(DeviceBuilder.getDeviceEntity().getStatus(), DeviceBuilder.getDeviceDto().status());
    }
}