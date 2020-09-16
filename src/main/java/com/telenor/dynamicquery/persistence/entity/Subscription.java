package com.telenor.dynamicquery.persistence.entity;

import com.telenor.dynamicquery.common.ProductProperty;
import com.telenor.dynamicquery.common.ProductType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("subscription")
@Entity
public class Subscription extends Product {

    @Column(name = "data_limit")
    private Long dataLimitInGB;

    public Subscription() {
    }

    @Override
    public ProductType getProductType() {
        return ProductType.SUBSCRIPTION;
    }

    @Override
    public ProductProperty getProductProperty() {
        return ProductProperty.GB_LIMIT;
    }

    public Long getDataLimitInGB() {
        return dataLimitInGB;
    }

    public void setDataLimitInGB(Long dataLimitInGB) {
        this.dataLimitInGB = dataLimitInGB;
    }
}
