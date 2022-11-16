package com.customer.service.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;
    protected  Device() {}

    public Device(UUID id,DeviceStatus status) {
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device device)) return false;
        return Objects.equals(getId(), device.getId()) && getStatus() == device.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus());
    }
}
