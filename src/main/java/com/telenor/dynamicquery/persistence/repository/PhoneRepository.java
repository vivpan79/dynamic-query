package com.telenor.dynamicquery.persistence.repository;

import com.telenor.dynamicquery.persistence.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>,
    JpaSpecificationExecutor<Phone> {

}
