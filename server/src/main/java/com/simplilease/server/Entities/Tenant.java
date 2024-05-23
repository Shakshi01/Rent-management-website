package com.simplilease.server.Entities;

import com.simplilease.server.Entities.Embeddables.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tenants")
public class Tenant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tenantId;

    @Column(nullable = false, unique = true)
    private String uname;

    @Column(nullable = false, unique = false)
    private String pswd;

    @Column(nullable = false)
    private Name name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pnumber;

    // rental property
    @Column(nullable = true, unique = false)
    private long propertyId;

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.pswd = password;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPnumber(String pNumber) {
        this.pnumber = pNumber;
    }

    public void setPropertyId(long pId) {
        this.propertyId = pId;
    }

    public long getTenantId() {
        return this.tenantId;
    }

    public String getUname() {
        return this.uname;
    }

    public String getPassword() {
        return this.pswd;
    }

    public Name getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPnumber() {
        return this.pnumber;
    }

    public long getPropertyId() {
        return this.propertyId;
    }

}
