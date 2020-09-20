package com.telenor.dynamicquery.controller;

import static com.telenor.dynamicquery.common.ProductType.PHONE;
import static com.telenor.dynamicquery.common.ProductType.SUBSCRIPTION;
import static java.util.stream.Collectors.toList;

import com.telenor.dynamicquery.controller.error.InvalidProductTypeException;
import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.service.PhoneService;
import com.telenor.dynamicquery.persistence.service.SubscriptionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private PhoneService phoneService;
    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Telenor's take-home assignment: Dynamic Query";
    }

    @GetMapping("/products")
    @ApiOperation(
        value = "Get Phone/Subscription information.",
        notes = "Get Phone/Subscription information.",
        response = ResponseData.class)
    public ResponseData findAllMatching(
        @ApiParam(value = "Product type (phone/subscription)")
        @RequestParam(name = "productType", required = false) String type,
        @ApiParam(value = "Minimum price of product")
        @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
        @ApiParam(value = "Maximum price of product")
        @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
        @ApiParam(value = "City where store is located")
        @RequestParam(name = "city", required = false) String city,
        @ApiParam(value = "Product property color/gd_limit. Note: Currently unused")
        @RequestParam(name = "property", required = false) String property,
        @ApiParam(value = "Phone color")
        @RequestParam(name = "productProperty:color", required = false) String color,
        @ApiParam(value = "Minimum data limit for subscription in GB")
        @RequestParam(name = "productProperty:gb_limit_min", required = false) Long minGBLimit,
        @ApiParam(value = "Maximum data limit for subscription in GB")
        @RequestParam(name = "productProperty:gb_limit_max", required = false) Long maxGBLimit
    ) {
        List<? extends Product> products;
        if (PHONE.name().equalsIgnoreCase(type)) {
            products = phoneService.findAll(minPrice, maxPrice, color, city);
        } else if (SUBSCRIPTION.name().equalsIgnoreCase(type)) {
            products = subscriptionService.findAll(minPrice, maxPrice, minGBLimit, maxGBLimit, city);
        } else {
            throw new InvalidProductTypeException("Invalid ProductType: " + type);
        }
        return new ResponseData(products.stream().map(RestProduct::new).collect(toList()));
    }
}
