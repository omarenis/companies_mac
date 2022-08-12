package com.mac_vendor.mac_vendor.controllers;

import com.mac_vendor.mac_vendor.models.entities.Company;
import com.mac_vendor.mac_vendor.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping("/companies")
    public Company getCompanyByMacAddressAndReference(@RequestParam String macAddress, @RequestParam String reference)
    {
        try
        {
            return companyService.findCompanyByMacAddressAndReference(macAddress, reference);
        } catch (Exception exception)
        {
            throw exception;
        }
    }
}
