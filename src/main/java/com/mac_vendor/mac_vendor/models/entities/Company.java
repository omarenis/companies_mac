package com.mac_vendor.mac_vendor.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name="name") private String name;
    @ManyToMany
    @JoinTable(
            name = "MAC_COMPANIES",
            joinColumns = @JoinColumn(name = "companies_id"),
            inverseJoinColumns = @JoinColumn(name = "mac_id"),
            uniqueConstraints = {@UniqueConstraint(name = "macs_companies_unique", columnNames = {"companies_id", "mac_id"})})

    private List<Mac> macList;

    public Company(String name) {
        this.name = name;
    }

    public Company() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mac> getMacList() {
        return macList;
    }

    public void setMacList(List<Mac> macList) {
        this.macList = macList;
    }
}
