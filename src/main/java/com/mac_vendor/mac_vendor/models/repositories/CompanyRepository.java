package com.mac_vendor.mac_vendor.models.repositories;

import com.mac_vendor.mac_vendor.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Company findCompanyByName(String name);
}
