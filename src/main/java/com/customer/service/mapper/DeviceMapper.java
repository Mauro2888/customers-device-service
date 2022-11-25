package com.customer.service.mapper;

import com.customer.service.model.dto.device.DeviceDto;
import com.customer.service.model.entity.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper extends EntityMapper<DeviceDto, Device> {}
