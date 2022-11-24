package com.customer.service.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public record CustomerDto(@NotBlank String name,
                          @NotBlank String cognome,
                          @NotBlank String codiceFiscale,
                          @NotBlank String indirizzo,
                          @Size(max = 2) List<DeviceDto> devices) implements Serializable {
}
