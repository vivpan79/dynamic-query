package com.telenor.dynamicquery.persistence.service;

import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }
}
