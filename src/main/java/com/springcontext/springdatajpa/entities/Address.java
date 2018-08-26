package com.springcontext.springdatajpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String street;

    @Column(nullable = false, columnDefinition = "INT(11) DEFAULT 0")
    private Integer streetNumber;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String zipCode;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String city;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String country;

    @OneToOne(mappedBy = "address")
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}