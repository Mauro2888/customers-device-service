package com.customer.service.model.entity;

import com.customer.service.utils.ListSizeMax;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @NotBlank
    private String name;
    @NotBlank
    private String cognome;
    @NotBlank
    @Id
    private String codiceFiscale;
    @NotBlank
    private String indirizzo;
    @ListSizeMax
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_codiceFiscale")
    private List<@Valid Device> devices;

    protected Customer() {
    }

    public Customer(String name, String cognome, String codiceFiscale, String indirizzo, List<@Valid Device> devices) {
        this.name = name;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.indirizzo = indirizzo;
        this.devices = devices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
