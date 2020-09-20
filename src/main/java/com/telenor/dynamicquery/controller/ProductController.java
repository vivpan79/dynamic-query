package com.telenor.dynamicquery.controller;

import static com.telenor.dynamicquery.common.ProductType.PHONE;
import static com.telenor.dynamicquery.common.ProductType.SUBSCRIPTION;
import static java.util.stream.Collectors.toList;

import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.service.PhoneService;
import com.telenor.dynamicquery.persistence.service.SubscriptionService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private PhoneService phoneService;
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/products")
    public ResponseData findAllMatching(
        @RequestParam(name = "productType", required = false) String type,
        @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
        @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "property", required = false) String property,
        @RequestParam(name = "productProperty:color", required = false) String color,
        @RequestParam(name = "productProperty:gb_limit_min", required = false) Long minGBLimit,
        @RequestParam(name = "productProperty:gb_limit_max", required = false) Long maxGBLimit
    ) {
        List<? extends Product> products;
        if (PHONE.name().equalsIgnoreCase(type)) {
            products = phoneService.findAll(minPrice, maxPrice, color, city);
        } else if (SUBSCRIPTION.name().equalsIgnoreCase(type)) {
            products = subscriptionService.findAll(minPrice, maxPrice, minGBLimit, maxGBLimit, city);
        } else {
            throw new IllegalArgumentException("Invalid ProductType: " + type);
        }
        return new ResponseData(products.stream().map(RestProduct::new).collect(toList()));
    }
}
