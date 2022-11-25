package com.customer.service.model.entity;

import com.customer.service.utils.CodiceFiscale;
import com.customer.service.utils.ListSizeMax;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank
    @CodiceFiscale
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

    public Customer(String nome, String cognome, String codiceFiscale, String indirizzo, List<@Valid Device> devices) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.indirizzo = indirizzo;
        this.devices = devices;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
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
