package com.simplilease.server.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rentPayments")
public class RentPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rpId;

    @Column(nullable = false, unique = true)
    private long propertyId;

    @Column(nullable = false, unique = true)
    private long tenantId;

    @Column(nullable = false, unique = false)
    private float amount;

    @Column(nullable = false, unique = false)
    private Date date;

    public void setPropertyId(long pId) {
        this.propertyId = pId;
    }

    public void setTenantId(long tId) {
        this.tenantId = tId;
    }

    public void setPaymentAmount(float amount) {
        this.amount = amount;
    }

    public void setPaymentDate(Date d) {
        this.date = d;
    }

    public long getRpId() {
        return this.rpId;
    }

    public long getPropertyId() {
        return this.propertyId;
    }

    public long getTenantId() {
        return this.tenantId;
    }

    public float getPaymentAmount() {
        return this.amount;
    }

    public Date getPaymentDate() {
        return this.date;
    }
    
}
