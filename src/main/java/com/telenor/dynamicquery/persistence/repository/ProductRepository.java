package com.telenor.dynamicquery.persistence.repository;

import com.telenor.dynamicquery.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
