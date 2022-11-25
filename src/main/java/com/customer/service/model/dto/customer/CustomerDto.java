package com.customer.service.model.dto.customer;

import com.customer.service.model.dto.device.DeviceDto;
import com.customer.service.utils.CodiceFiscale;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public record CustomerDto(@NotBlank String nome,
                          @NotBlank String cognome,
                          @NotBlank @CodiceFiscale String codiceFiscale,
                          @NotBlank String indirizzo,
                          @Size(max = 2) List<DeviceDto> devices){
}
