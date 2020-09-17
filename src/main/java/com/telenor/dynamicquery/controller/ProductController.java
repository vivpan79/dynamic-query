package com.telenor.dynamicquery.controller;

import static java.util.stream.Collectors.toList;

import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseData findAll() {

        List<Product> products = service.findAll();
        return new ResponseData(products.stream().map(RestProduct::new)
            .collect(toList()));
    }
}
