package com.telenor.dynamicquery.persistence.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.entity.Subscription;
import com.telenor.dynamicquery.persistence.service.ProductService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
        throws Exception {

        Product phone = new Phone();
        Subscription subscription = new Subscription();

        List<Product> products = Arrays.asList(phone, subscription);

        given(service.findAll()).willReturn(products);

        mvc.perform(get("/product/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].productProperty", is(phone.getProductProperty().name())))
            .andExpect(jsonPath("$[0].productType", is(phone.getProductType().name())))
            .andExpect(jsonPath("$[1].productProperty", is(subscription.getProductProperty().name())))
            .andExpect(jsonPath("$[1].productType", is(subscription.getProductType().name())));
    }
}
