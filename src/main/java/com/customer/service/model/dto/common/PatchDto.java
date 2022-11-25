package com.customer.service.model.dto.common;

import com.customer.service.model.entity.DeviceStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public enum PatchDto {;
    private interface Indirizzo { @NotBlank String getIndirizzo();}
    private interface Status {@NotNull DeviceStatus getStatus();}

    public enum Request {;

        public static class updateIndirizzo implements Indirizzo {
            private String indirizzo;

            public updateIndirizzo(String indirizzo) {
                this.indirizzo = indirizzo;
            }

            public updateIndirizzo() {
            }

            @Override
            public String getIndirizzo() {
                return indirizzo;
            }

        }

        public static class updateStatus implements Status {

            private DeviceStatus status;

            public updateStatus(DeviceStatus status) {
                this.status = status;
            }

            public updateStatus() {
            }

            @Override
            public DeviceStatus getStatus() {
                return status;
            }

        }
    }
}
