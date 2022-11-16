package com.customer.service.model.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public record CustomerDto(@NotBlank String name,
                          @NotBlank String cognome,
                          @NotBlank String codiceFiscale,
                          @NotBlank String indirizzo,
                          List<DeviceDto> devices) implements Serializable {
}
