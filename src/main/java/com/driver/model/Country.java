package com.driver.model;
// Note: Do not write @Enumerated annotation above CountryName in this model.

import javax.persistence.*;

@Entity
public  class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Enumerated(value=EnumType.STRING)
    private CountryName countryName;
    private String codes;

@OneToOne
@JoinColumn
User user;

@ManyToOne
    @JoinColumn
ServiceProvider serviceProvider;

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
