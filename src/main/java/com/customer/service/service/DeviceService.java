package com.customer.service.service;

import com.customer.service.exception.CustomerNotFoundException;
import com.customer.service.mapper.DeviceMapper;
import com.customer.service.model.dto.device.DeviceDto;
import com.customer.service.model.dto.common.PatchDto;
import com.customer.service.model.entity.Device;
import com.customer.service.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for managing devices.
 */
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final Logger LOG = LoggerFactory.getLogger(DeviceService.class);

    public DeviceService(DeviceRepository deviceRepository,
                         DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Find device by id
     * @param id device id
     * @return device dto
     */
    public DeviceDto getDevice(UUID id) {
        return deviceRepository.findById(id)
                .map(deviceMapper::toDto)
                .orElseThrow(() -> {
                    LOG.error("Device not found {}", id);
                    return new CustomerNotFoundException("Device not found");
                });
    }

    /**
     * Delete device
     * @param id device id
     */
    public void deleteDevice(UUID id) {
        getDevice(id);
        LOG.info("Device deleted {}", id);
        deviceRepository.deleteById(id);
    }

    /**
     * Update device indirizzo
     * @param uuid device id
     * @param status device status
     * @return updated device dto
     */
    public DeviceDto updateDeviceStatus(String uuid, PatchDto.Request.updateStatus status) {
        Device device = deviceRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> {
                    LOG.error("Device not found {}", uuid);
                    return new CustomerNotFoundException("Device not found");
                });

        device.setStatus(status.getStatus());
        deviceRepository.save(device);
        LOG.info("Device status updated {}", uuid);
        return deviceMapper.toDto(device);
    }
}
