package com.telenor.dynamicquery.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.telenor.dynamicquery.Application;
import com.telenor.dynamicquery.persistence.entity.Product;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void givenProductRepositoryWhenSaveAndRetrieveEntityThenOK(){
        Product entity = new Product();
        entity.setStoreAddress("anywhere");
        Product product = repository.save(entity);
        Optional<Product> retrievedProduct = repository.findById(product.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals("anywhere", retrievedProduct.get().getStoreAddress());
    }
}