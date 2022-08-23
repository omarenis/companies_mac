package com.mac_vendor.mac_vendor.models.repositories;

import com.mac_vendor.mac_vendor.models.entities.Mac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MacRepository extends JpaRepository<Mac, Long> {
    Mac findByValueContains(String value);
    List<Mac> findAllByValueContains(String value);
}
