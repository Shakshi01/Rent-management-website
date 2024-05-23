package com.simplilease.server.Entities.Embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {
    
    private String fname;
    private String lname;

    public Name() {}

    public Name(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }
}
