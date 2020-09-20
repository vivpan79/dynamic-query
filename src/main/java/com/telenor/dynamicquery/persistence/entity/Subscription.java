package com.telenor.dynamicquery.persistence.entity;

import static com.telenor.dynamicquery.common.ProductProperty.GB_LIMIT;
import static com.telenor.dynamicquery.common.ProductType.SUBSCRIPTION;

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
        return SUBSCRIPTION;
    }

    @Override
    public ProductProperty getProductProperty() {
        return GB_LIMIT;
    }

    public Long getDataLimitInGB() {
        return dataLimitInGB;
    }

    public void setDataLimitInGB(Long dataLimitInGB) {
        this.dataLimitInGB = dataLimitInGB;
    }
}
