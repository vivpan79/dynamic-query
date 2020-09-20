package com.telenor.dynamicquery.persistence.service;

import static com.telenor.dynamicquery.common.ProductType.PHONE;
import static com.telenor.dynamicquery.common.ProductType.SUBSCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.telenor.dynamicquery.Application;
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
        List<? extends Product> retrievedProducts = phoneService.findAll("277.00", "277.00", null, null);

        assertFalse(retrievedProducts.isEmpty());
        assertEquals(1, retrievedProducts.size());
        Product product = retrievedProducts.get(0);
        assertEquals(PHONE, product.getProductType());
        assertEquals("Blake gränden, Karlskrona", product.getStoreAddress());
        assertEquals("grön", ((Phone) product).getColor());
    }

    @Test
    void givenProductServiceWhenFindPhoneByPriceRangeThenPhoneRetrieved() {
        List<Phone> retrievedProducts = phoneService.findAll("276.00", "278.00", null, null);

        assertFalse(retrievedProducts.isEmpty());
        assertEquals(1, retrievedProducts.size());
        Phone phone = retrievedProducts.get(0);
        assertEquals(PHONE, phone.getProductType());
        assertEquals("Blake gränden, Karlskrona", phone.getStoreAddress());
        assertEquals("grön", phone.getColor());
    }

    @Test
    void givenProductServiceWhenFindPhoneByColorThenPhoneRetrieved() {
        List<Phone> retrievedProducts = phoneService.findAll(null, null, "grön", null);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(5, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> PHONE.equals(product.getProductType())
                && "grön".equals(product.getColor())
        ));
    }

    @Test
    void givenProductServiceWhenFindPhoneByCityAddressThenPhoneRetrieved() {
        List<Phone> retrievedProducts = phoneService.findAll(null, null, null, "Karlskrona");
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(11, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> PHONE.equals(product.getProductType())
                && product.getStoreAddress().contains("Karlskrona")
        ));
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByExactPriceMatchThenSubscriptionRetrieved() {
        List<Subscription> retrievedProducts = subscriptionService
            .findAll("415.00", "415.00", null, null, null);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(1, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> SUBSCRIPTION.equals(product.getProductType())
                && 415.00 == product.getPrice().doubleValue()
        ));
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByPriceRangeThenSubscriptionRetrieved() {
        List<Subscription> retrievedProducts = subscriptionService
            .findAll("401.00", "499.00", null, null, null);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(10, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> SUBSCRIPTION.equals(product.getProductType())
                && 499.00 >= product.getPrice().doubleValue()
                && 401.00 <= product.getPrice().doubleValue()
        ));
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByExactDataLimitMatchThenSubscriptionRetrieved() {
        List<Subscription> retrievedProducts = subscriptionService
            .findAll(null, null, 50L, 50L, null);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(34, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> SUBSCRIPTION.equals(product.getProductType())
                && 50L == product.getDataLimitInGB()
        ));
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByDataLimitRangeThenSubscriptionRetrieved() {
        List<Subscription> retrievedProducts = (List<Subscription>) subscriptionService
            .findAll(null, null, 10L, 50L, null);
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(58, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> SUBSCRIPTION.equals(product.getProductType())
                && 50L >= product.getDataLimitInGB()
                && 10L <= product.getDataLimitInGB()
        ));
    }

    @Test
    void givenProductServiceWhenFindSubscriptionByCityAddressThenSubscriptionRetrieved() {
        List<Subscription> retrievedProducts = subscriptionService
            .findAll(null, null, null, null, "Karlskrona");
        assertFalse(retrievedProducts.isEmpty());
        assertEquals(18, retrievedProducts.size());
        assertTrue(retrievedProducts.stream().allMatch(
            product -> SUBSCRIPTION.equals(product.getProductType())
                && product.getStoreAddress().contains("Karlskrona")
        ));
    }
}
