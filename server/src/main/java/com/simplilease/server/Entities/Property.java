package com.simplilease.server.Entities;

import com.simplilease.server.Entities.Embeddables.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false, unique = false)
    private String pname;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false, unique = false)
    private long ownerId ;

    @Column(nullable = false, unique = false)
    private float size;

    @Column(nullable = false, unique = false)
    private int bedrooms;

    @Column(nullable = false, unique = false)
    private int bathrooms;

    @Column(nullable = true, unique = false)
    private String description;

    @Column(nullable = false, unique = false)
    private String rentStatus;

    @Column(nullable = true, unique = false)
    private float rentPrice;
    
    public void setPname(String pName) {
        this.pname = pName;
    }

    public void setAddress(Address a) {
        this.address = a;
    }

    public void setOwner(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setBedrooms(int bdr) {
        this.bedrooms = bdr;
    }

    public void setBathrooms(int btr) {
        this.bathrooms = btr;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public void setRentStatus(String status) {
        this.rentStatus = status;
    }

    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    public long getPId() {
        return this.pid;
    }

    public String getPname() {
        return this.pname;
    }

    public Address getAddress() {
        return this.address;
    }

    public long getOwnerId() {
        return this.ownerId;
    }

    public float getSize() {
        return this.size;
    }

    public int getBedrooms() {
        return this.bedrooms;
    }

    public int getBathrooms() {
        return this.bathrooms;
    }

    public String getDescription() {
        return this.description;
    }

    public String getRentStatus() {
        return this.rentStatus;
    }

    public float getRentPrice() {
        return this.rentPrice;
    }

}
