package com.telenor.dynamicquery.persistence.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.repository.PhoneRepository;
import com.telenor.dynamicquery.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(PhoneService.class)
class ProductServiceTest {

    @MockBean
    private PhoneRepository repository;

    @Autowired
    private PhoneService service;

    @Test
    void givenProductServiceWhenSaveAndRetrieveEntityThenOK() {
        when(repository.save(any())).thenReturn(new Phone());
        Product product = service.save(new Phone());
        assertNotNull(product);
    }
}
