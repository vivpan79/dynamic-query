package com.telenor.dynamicquery.persistence.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.telenor.dynamicquery.Application;
import com.telenor.dynamicquery.common.ProductType;
import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.entity.Subscription;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class ProductServiceIntegrationTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PhoneService phoneService;

    @Test
    void givenProductServiceWhenFindByPhoneByExactPriceMatchThenPhoneRetrieved() {
        List<? extends Product> retrievedProducts = phoneService.findAllPhones("277.00", "277.00", new Phone());
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(1, retrievedProducts.size());
        assertEquals(ProductType.PHONE, retrievedProducts.get(0).getProductType());
        assertEquals("Blake gränden, Karlskrona", retrievedProducts.get(0).getStoreAddress());
        assertEquals("grön", ((Phone) retrievedProducts.get(0)).getColor());
    }

    @Test
    void givenProductServiceWhenFindPhoneByPriceRangeThenPhoneRetrieved() {
        Product product = phoneService.save(new Phone());
        List<Phone> retrievedProducts = (List<Phone>) phoneService.findAllPhones("276.00", "278.00", (Phone)product);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(1, retrievedProducts.size());
        assertEquals(ProductType.PHONE, retrievedProducts.get(0).getProductType());
        assertEquals("Blake gränden, Karlskrona", retrievedProducts.get(0).getStoreAddress());
        assertEquals("grön", ((Phone) retrievedProducts.get(0)).getColor());
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByExactPriceMatchThenSubscriptionRetrieved() {
        List<? extends Product> retrievedProducts = subscriptionService.findAllSubscription(null, null, 50L, 50L, new Subscription());
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(34, retrievedProducts.size());
        assertEquals(ProductType.SUBSCRIPTION, retrievedProducts.get(0).getProductType());
        assertEquals(50, ((Subscription) retrievedProducts.get(0)).getDataLimitInGB());
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByPriceRangeThenSubscriptionRetrieved() {
        List<Subscription> retrievedProducts = (List<Subscription>) subscriptionService
            .findAllSubscription(null, null, 30L, 60L, new Subscription());
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(34, retrievedProducts.size());
        assertEquals(ProductType.SUBSCRIPTION, retrievedProducts.get(0).getProductType());
        assertEquals(50, ((Subscription) retrievedProducts.get(0)).getDataLimitInGB());
    }
}
