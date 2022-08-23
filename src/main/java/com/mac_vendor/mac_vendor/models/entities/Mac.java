package com.mac_vendor.mac_vendor.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mac {
    @Id @GeneratedValue private Long id;
    @Column(name="value") private String value;

    @ManyToOne
    private Company company;

    public Mac(String value) {
        this.value = value;
    }

    public Mac() {}

    public Mac(String addressMac, Company company) {
        this.value = addressMac;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
