package com.telenor.dynamicquery.persistence.entity;

import com.telenor.dynamicquery.common.ProductType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_address")
    private String storeAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType productType;

    @Column(name = "price")
    private BigDecimal price;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
