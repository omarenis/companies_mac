package com.mac_vendor.mac_vendor.services;

import com.mac_vendor.mac_vendor.models.entities.Company;
import com.mac_vendor.mac_vendor.models.entities.Mac;
import com.mac_vendor.mac_vendor.models.repositories.MacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private final MacRepository macRepository;

    @Autowired
    public CompanyService(MacRepository macRepository)
    {
        this.macRepository = macRepository;
    }

    private Company findCompanyInCompanies(List<Company> companies, Company company)
    {
        if(companies != null)
        {
            for (Company company1 :
                    companies) {
                if (company1.getName().equals(company.getName()))
                {
                    return null;
                }
            }
        }
        return company;
    }
    public List<Company> findCompanyByMacAddress(String macAddress) throws Exception
    {
        List<Company> output = new ArrayList<>();
        List<Mac> macList = macRepository.findAllByValueContains(macAddress);
        if(macList != null)
        {
            for (Mac mac :
                    macList) {
                Company company = findCompanyInCompanies(output, mac.getCompany());
                if(company != null)
                {
                    output.add(company);
                }
            }
        }
        return output;
    }
}
