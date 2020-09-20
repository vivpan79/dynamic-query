package com.telenor.dynamicquery.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.telenor.dynamicquery.Application;
import com.telenor.dynamicquery.common.ProductProperty;
import com.telenor.dynamicquery.common.ProductType;
import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.entity.Subscription;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void givenProductRepositoryWhenSaveAndRetrieveProductEntityThenOK() {
        Product entity = new Phone();
        entity.setStoreAddress("anywhere");
        entity.setPrice(BigDecimal.valueOf(123.12));
        Product product = repository.save(entity);
        Optional<Product> retrievedProduct = repository.findById(product.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals("anywhere", retrievedProduct.get().getStoreAddress());
        assertEquals(ProductType.PHONE, retrievedProduct.get().getProductType());
        assertEquals(123.12, retrievedProduct.get().getPrice().doubleValue());
    }

    @Test
    void givenProductRepositoryWhenSaveAndRetrievePhoneEntityThenOK() {
        Phone entity = new Phone();
        entity.setColor("Blue");
        Phone product = repository.save(entity);
        Optional<Product> retrievedProduct = repository.findById(product.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals(ProductType.PHONE, retrievedProduct.get().getProductType());
        assertTrue(retrievedProduct.get() instanceof Phone);
        Phone phone = (Phone) retrievedProduct.get();
        assertEquals("Blue", phone.getColor());
        assertEquals(ProductProperty.COLOR, phone.getProductProperty());
    }

    @Test
    void givenProductRepositoryWhenSaveAndRetrieveSubscriptionEntityThenOK1() {
        Subscription entity = new Subscription();
        entity.setDataLimitInGB(123L);
        Subscription product = repository.save(entity);
        Optional<Product> retrievedProduct = repository.findById(product.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals(ProductType.SUBSCRIPTION, retrievedProduct.get().getProductType());
        assertTrue(retrievedProduct.get() instanceof Subscription);
        Subscription subscription = (Subscription) retrievedProduct.get();
        assertEquals(123L, subscription.getDataLimitInGB());
        assertEquals(ProductProperty.GB_LIMIT, subscription.getProductProperty());
    }

    @Test
    void givenProductRepositoryWhenQueryByExampleSubscriptionEntityThenOK() {
        Subscription subscription1 = new Subscription();
        subscription1.setPrice(new BigDecimal("321.12"));
        repository.save(subscription1);
        Subscription subscription2 = new Subscription();
        subscription2.setPrice(new BigDecimal("321.11"));
        repository.save(subscription2);
        Subscription queryByExample = new Subscription();
        queryByExample.setPrice(new BigDecimal("321.12"));
        List<Subscription> retrievedProducts = repository.findAll(Example.of(queryByExample));
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(1, retrievedProducts.size());
        assertEquals(ProductType.SUBSCRIPTION, retrievedProducts.get(0).getProductType());
    }
}