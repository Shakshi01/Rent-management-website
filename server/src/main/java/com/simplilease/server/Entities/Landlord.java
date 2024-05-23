package com.simplilease.server.Entities;

import com.simplilease.server.Entities.Embeddables.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Landlords")
public class Landlord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long llId;

    @Column(nullable = false, unique = true)
    private String uname;

    @Column(nullable = false, unique = false)
    private String password;

    @Embedded
    private Name name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pNumber;


    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String pswd) {
        this.password = pswd;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPnumber(String pNumber) {
        this.pNumber = pNumber;
    }


    public long getLlId() {
        return this.llId;
    }

    public String getUname() {
        return this.uname;
    }

    public String getPassword() {
        return this.password;
    }

    public Name getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPnumber() {
        return this.pNumber;
    }
}
