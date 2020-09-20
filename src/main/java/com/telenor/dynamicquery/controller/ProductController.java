package com.telenor.dynamicquery.controller;

import static com.telenor.dynamicquery.common.ProductType.PHONE;
import static com.telenor.dynamicquery.common.ProductType.SUBSCRIPTION;
import static java.util.stream.Collectors.toList;

import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.service.PhoneService;
import com.telenor.dynamicquery.persistence.service.SubscriptionService;
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
        @RequestParam(name = "min_price", required = false) String minPrice,
        @RequestParam(name = "max_price", required = false) String maxPrice,
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "property", required = false) String property,
        @RequestParam(name = "productProperty:color", required = false) String color,
        @RequestParam(name = "productProperty:gb_limit_min", required = false) String minimumGBLimit,
        @RequestParam(name = "productProperty:gb_limit_max", required = false) String maxGBLimit
    ) {
        List<? extends Product> products;
        if (type.equalsIgnoreCase(PHONE.name())) {
            products = phoneService.findAll(minPrice, maxPrice, color, city);
        } else if (type.equalsIgnoreCase(SUBSCRIPTION.name())) {
            products = subscriptionService.findAll(minPrice, maxPrice, minimumGBLimit, maxGBLimit, city);
        } else {
            throw new IllegalArgumentException("Invalid ProductType: " + type);
        }
        return new ResponseData(products.stream().map(RestProduct::new).collect(toList()));
    }
}
