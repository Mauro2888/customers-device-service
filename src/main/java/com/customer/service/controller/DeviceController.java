package com.customer.service.controller;

import com.customer.service.model.dto.DeviceDto;
import com.customer.service.model.dto.PatchDto;
import com.customer.service.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(value = "{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto find(@PathVariable UUID uuid) {
        return deviceService.getDevice(uuid);
    }

    @DeleteMapping(value = "{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        deviceService.deleteDevice(uuid);
    }

    @PatchMapping(value = "{uuid}/stato")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeviceDto> update(@PathVariable String uuid,
                                            @RequestBody @Valid PatchDto.Request.updateStatus status) {
        DeviceDto device = deviceService.updateDeviceStatus(uuid, status);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(device.status())
                .toUri();
        return ResponseEntity.created(uri).body(device);
    }
}