package com.telenor.dynamicquery.persistence.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ProductService.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository repository;

    @Autowired
    private ProductService service;

    @Test
    void givenProductServiceWhenSaveAndRetrieveEntityThenOK() {
        when(repository.save(any(Product.class))).thenReturn(new Product());
        Product product = service.save(new Product());
        assertNotNull(product);
    }
}
