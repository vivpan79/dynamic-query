package com.telenor.dynamicquery.controller;

import static com.telenor.dynamicquery.common.ProductType.PHONE;
import static com.telenor.dynamicquery.common.ProductType.SUBSCRIPTION;

import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.entity.Product;
import com.telenor.dynamicquery.persistence.entity.Subscription;
import java.math.BigDecimal;

public class RestProduct {

    private final String productType;
    private final String properties;
    private final BigDecimal price;
    private final String store_address;

    public RestProduct(Product product) {
        this.productType = getProductType(product);
        this.properties = getProductProperty(product) + ":" + getvalue(product);
        this.price = product.getPrice();
        this.store_address = product.getStoreAddress();
    }

    private String getProductProperty(Product product) {
        return product.getProductProperty().name().toLowerCase();
    }

    private String getProductType(Product product) {
        return product.getProductType().name().toLowerCase();
    }

    String getvalue(Product product) {
        if (PHONE.equals(product.getProductType())) {
            String color = ((Phone) product).getColor();
            if (color == null || color.isEmpty()) {
                throw new IllegalArgumentException("Color for phone missing!");
            }
            return color;
        }
        if (SUBSCRIPTION.equals(product.getProductType())) {
            Long dataLimitInGB = ((Subscription) product).getDataLimitInGB();
            if (dataLimitInGB == null) {
                throw new IllegalArgumentException("Data Limit In GB for subscription missing!");
            }
            return dataLimitInGB.toString();
        }
        throw new IllegalArgumentException("Unknown product type!");
    }

    public String getProductType() {
        return productType;
    }

    public String getProperties() {
        return properties;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStore_address() {
        return store_address;
    }
}
