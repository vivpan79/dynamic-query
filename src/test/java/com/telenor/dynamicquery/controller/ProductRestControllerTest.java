package com.telenor.dynamicquery.controller;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Subscription;
import com.telenor.dynamicquery.persistence.service.PhoneService;
import com.telenor.dynamicquery.persistence.service.SubscriptionService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@ActiveProfiles("dev")
class ProductRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneService phoneService;

    @MockBean
    private SubscriptionService subscriptionService;

    @Test
    void givenPhoneServiceWhenGetProductsThenReturnJsonArray() throws Exception {
        Phone phone = new Phone();
        phone.setColor("blue");
        List<Phone> products = Collections.singletonList(phone);
        given(phoneService.findAll(valueOf(100), valueOf(1000), "blue", "Stockholm")).willReturn(products);
        mvc.perform(get("/products")
            .param("productType", "phone")
            .param("min_price", "100")
            .param("max_price", "1000")
            .param("city", "Stockholm")
            .param("productProperty:color", "blue")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data", hasSize(1)))
            .andExpect(jsonPath("$.data[0].productType", is("phone")))
            .andExpect(jsonPath("$.data[0].properties", is("color:blue")));
    }

    @Test
    void givenSubscriptionServiceWhenGetProductsThenReturnJsonArray() throws Exception {
        Subscription subscription = new Subscription();
        subscription.setDataLimitInGB(50L);
        List<Subscription> products = Collections.singletonList(subscription);
        given(subscriptionService.findAll(valueOf(100), valueOf(1000), 50L, 50L, "Stockholm")).willReturn(products);

        mvc.perform(get("/products")
            .param("productType", "subscription")
            .param("min_price", "100")
            .param("max_price", "1000")
            .param("city", "Stockholm")
            .param("productProperty:gb_limit_min", "50")
            .param("productProperty:gb_limit_max", "50")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data", hasSize(1)))
            .andExpect(jsonPath("$.data[0].productType", is("subscription")))
            .andExpect(jsonPath("$.data[0].properties", is("gb_limit:50")));
    }

    @Test
    void givenSubscriptionServiceWhenWrongProductTypeThenReturnBadRequest() throws Exception {
        mvc.perform(get("/products")
            .param("productType", "SomeThing")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void givenRestServiceWhenRootUrlThenReturnWelcomeMessage() throws Exception {
        mvc.perform(get("/")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is("Welcome to Telenor's take-home assignment: Dynamic Query")));
    }
}
