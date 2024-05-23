package com.simplilease.server.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "serviceRequests")
public class ServiceRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long srId;

    @Column(nullable = false, unique = false)
    private long pid;

    @Column(nullable = false, unique = false)
    private String type;

    @Column(nullable = false, unique = false)
    private String description;

    @Column(nullable = false, unique = false)
    private Date date;

    @Column(nullable = false, unique = false)
    private String status;

    public void setPropertyId(long pId) {
        this.pid  = pId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setDate(Date d) {
        this.date = d;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSrId() {
        return this.srId;
    }

    public long getPropertyId() {
        return this.pid;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() {
        return this.date;
    }

    public String getStatus() {
        return this.status;
    }
    

}
