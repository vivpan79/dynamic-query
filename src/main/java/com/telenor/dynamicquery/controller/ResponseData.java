package com.telenor.dynamicquery.controller;

import java.util.List;

public class ResponseData {

    private final List<RestProduct> data;

    public ResponseData(List<RestProduct> data) {
        this.data = data;
    }

    public List<RestProduct> getData() {
        return data;
    }
}
