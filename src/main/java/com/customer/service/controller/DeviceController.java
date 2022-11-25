package com.customer.service.controller;

import com.customer.service.model.dto.device.DeviceDto;
import com.customer.service.model.dto.common.PatchDto;
import com.customer.service.service.DeviceService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final Logger LOG = Logger.getLogger(DeviceController.class.getName());

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device Found"),
            @ApiResponse(responseCode = "404", description = "Device Not Found")
    })
    @GetMapping(value = "{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto find(@PathVariable UUID uuid) {
        LOG.info("Search Device : %s".formatted(uuid));
        return deviceService.getDevice(uuid);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Device Not Found"),
            @ApiResponse(responseCode = "204", description = "Device Deleted")
    })
    @DeleteMapping(value = "{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        LOG.info("Delete Device : %s".formatted(uuid));
        deviceService.deleteDevice(uuid);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device indirizzo updated"),
            @ApiResponse(responseCode = "404", description = "Device Not Found")
    })
    @PatchMapping(value = "{uuid}/stato")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeviceDto> update(@PathVariable String uuid,
                                            @RequestBody @Valid PatchDto.Request.updateStatus status) {
        DeviceDto device = deviceService.updateDeviceStatus(uuid, status);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(device.status())
                .toUri();
        LOG.info("Device updated: " + device);
        return ResponseEntity.created(uri).body(device);
    }
}
