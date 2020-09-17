package com.telenor.dynamicquery.persistence.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Subscription;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class RestProductTest {

    @Test
    void givenPhoneEntityWhenConvertedToRestProductThenTypeInitialized() {
        Phone phone = new Phone();
        phone.setColor("Blue");
        RestProduct restProduct = new RestProduct(phone);

        assertNotNull(restProduct.getProductType());
        assertEquals("phone", restProduct.getProductType());
    }

    @Test
    void givenSubscriptionEntityWhenConvertedToRestProductThenTypeInitialized() {
        Subscription subscription = new Subscription();
        subscription.setDataLimitInGB(10L);
        RestProduct restProduct = new RestProduct(subscription);

        assertNotNull(restProduct.getProductType());
        assertEquals("subscription", restProduct.getProductType());
    }

    @Test
    void givenPhoneEntityWhenConvertedToRestProductThenPropertyInitialized() {
        Phone phone = new Phone();
        phone.setColor("blue");
        RestProduct restProduct = new RestProduct(phone);

        assertNotNull(restProduct.getProperties());
        assertEquals("color:blue", restProduct.getProperties());
    }

    @Test
    void givenSubscriptionEntityWhenConvertedToRestProductThenPropertyInitialized() {
        Subscription subscription = new Subscription();
        subscription.setDataLimitInGB(10L);
        RestProduct restProduct = new RestProduct(subscription);

        assertNotNull(restProduct.getProperties());
        assertEquals("gb_limit:10", restProduct.getProperties());
    }

    @Test
    void givenSubscriptionEntityWithMissingDataLimitWhenConvertedToRestProductThenException() {
        assertThrows(IllegalArgumentException.class, () -> {
            RestProduct restProduct = new RestProduct(new Subscription());
        });
    }

    @Test
    void givenPhoneEntityWithMissingColorWhenConvertedToRestProductThenException() {
        assertThrows(IllegalArgumentException.class, () -> {
            RestProduct restProduct = new RestProduct(new Phone());
        });
    }

    @Test
    void givenPhoneEntityWhenConvertedToRestProductThenPriceAndAddressInitialized() {
        Phone phone = new Phone();
        phone.setColor("Blue");
        BigDecimal price = BigDecimal.valueOf(1234.34);
        phone.setPrice(price);
        phone.setStoreAddress("Some Where");
        RestProduct restProduct = new RestProduct(phone);

        assertNotNull(restProduct.getProperties());
        assertEquals(price, restProduct.getPrice());
        assertEquals("Some Where", restProduct.getStore_address());
    }
}