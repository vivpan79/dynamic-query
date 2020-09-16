package com.telenor.dynamicquery.persistence.entity;

import com.telenor.dynamicquery.common.ProductType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue(value = "phone")
@Entity
public class Phone extends Product{

    @Column(name = "color")
    private String color;

    public Phone() {
    }

    @Override
    public ProductType getProductType() {
        return ProductType.PHONE;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
