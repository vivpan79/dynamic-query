package com.telenor.dynamicquery.persistence.entity;

import static com.telenor.dynamicquery.common.ProductProperty.COLOR;
import static com.telenor.dynamicquery.common.ProductType.PHONE;

import com.telenor.dynamicquery.common.ProductProperty;
import com.telenor.dynamicquery.common.ProductType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue(value = "phone")
@Entity
public class Phone extends Product {

    @Column(name = "color")
    private String color;

    public Phone() {
    }

    @Override
    public ProductType getProductType() {
        return PHONE;
    }

    @Override
    public ProductProperty getProductProperty() {
        return COLOR;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
