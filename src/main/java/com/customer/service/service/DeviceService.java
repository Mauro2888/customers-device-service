package com.customer.service.service;

import com.customer.service.exception.CustomerNotFoundException;
import com.customer.service.mapper.DeviceMapper;
import com.customer.service.model.dto.DeviceDto;
import com.customer.service.model.dto.PatchDto;
import com.customer.service.model.entity.Device;
import com.customer.service.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public DeviceService(DeviceRepository deviceRepository,
                         DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    public DeviceDto getDevice(UUID id) {
        return deviceRepository.findById(id)
                .map(deviceMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Device not found"));
    }

    public void deleteDevice(UUID id) {
        getDevice(id);
        deviceRepository.deleteById(id);
    }

    public DeviceDto updateDeviceStatus(String uuid, PatchDto.Request.updateStatus status) {
        Device device = deviceRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new CustomerNotFoundException("Device not found"));
        device.setStatus(status.getStatus());
        deviceRepository.save(device);
        return deviceMapper.toDto(device);
    }
}
