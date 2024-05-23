package com.simplilease.server.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseId;

    @Column(nullable = false, unique = false)
    private long propertyId;

    @Column(nullable = false, unique = false)
    private String expenseType;

    @Column(nullable = false, unique = false)
    private float cost;

    @Column(nullable = false, unique = false)
    private Date date;

    @Column(nullable = true, unique = false)
    private String description;


    public void setProperty(long pId) {
        this.propertyId = pId;
    }

    public void setExpenseType(String type) {
        this.expenseType = type;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setDate(Date d) {
        this.date = d;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public long getExpenseId() {
        return this.expenseId;
    }

    public long getPropertyId() { 
        return this.propertyId;
    }

    public String getExpenseType() {
        return this.expenseType;
    }

    public float getCost() {
        return this.cost;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }


}
