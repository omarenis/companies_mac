package com.mac_vendor.mac_vendor.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "mac_companies", uniqueConstraints={
        @UniqueConstraint( name = "idx_col1_col2",  columnNames ={"value","reference"})
})
public class Mac {
    @Id @GeneratedValue private Long id;
    @Column(name="value") private String value;
    @Column(name = "reference") private String reference;
    @ManyToOne private Company company;

    public Mac(String value, String reference, Company company) {
        this.value = value;
        this.reference = reference;
        this.company = company;
    }

    public Mac() {}

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
