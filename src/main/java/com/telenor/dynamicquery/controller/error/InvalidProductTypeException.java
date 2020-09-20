package com.telenor.dynamicquery.controller.error;

public class InvalidProductTypeException extends RuntimeException {

    public InvalidProductTypeException(String message) {
        super(message);
    }
}
