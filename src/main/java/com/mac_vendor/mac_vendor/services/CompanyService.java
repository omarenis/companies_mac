package com.mac_vendor.mac_vendor.services;

import com.mac_vendor.mac_vendor.models.entities.Company;
import com.mac_vendor.mac_vendor.models.repositories.MacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final MacRepository macRepository;

    @Autowired
    public CompanyService(MacRepository macRepository)
    {
        this.macRepository = macRepository;
    }

    public Company findCompanyByMacAddress(String macAddress) throws Exception
    {
        return this.macRepository.findByValueContains(macAddress).getCompany();
    }

    public Company findCompanyByMacAddressAndReference(String macAddress, String reference)
    {
        return this.macRepository.findByValueContainsAndReferenceContains(macAddress, reference).getCompany();
    }
}
