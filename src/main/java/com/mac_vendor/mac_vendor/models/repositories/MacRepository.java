package com.mac_vendor.mac_vendor.models.repositories;

import com.mac_vendor.mac_vendor.models.entities.Company;
import com.mac_vendor.mac_vendor.models.entities.Mac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MacRepository extends JpaRepository<Mac, Long> {
    public Mac findByValueContains(String value);
    public Mac findByValueContainsAndReferenceContains(String value, String reference);
}
