package com.simplilease.server.Entities.Embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zip;
    private String country;

    public void setLine1(String l1) {
        this.line1 = l1;
    }

    public void setLine2(String l2) {
        this.line2 = l2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLine1() {
        return this.line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZip() {
        return this.zip;
    }

    public String getCountry() {
        return this.country;
    }

}
