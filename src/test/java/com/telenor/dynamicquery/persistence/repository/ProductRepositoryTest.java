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
        Product product = repository.save(new Product());
        Optional<Product> retrievedProduct = repository.findById(product.getId());
        assertNotNull(retrievedProduct);
    }
}