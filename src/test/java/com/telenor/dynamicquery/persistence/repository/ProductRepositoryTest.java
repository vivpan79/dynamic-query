package com.telenor.dynamicquery.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.telenor.dynamicquery.Application;
import com.telenor.dynamicquery.common.ProductType;
import com.telenor.dynamicquery.persistence.entity.Product;
import java.math.BigDecimal;
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
        entity.setProductType(ProductType.PHONE);
        entity.setPrice(BigDecimal.valueOf(123.12));
        Product product = repository.save(entity);
        Optional<Product> retrievedProduct = repository.findById(product.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals("anywhere", retrievedProduct.get().getStoreAddress());
        assertEquals(ProductType.PHONE, retrievedProduct.get().getProductType());
        assertEquals(123.12, retrievedProduct.get().getPrice().doubleValue());
    }
}