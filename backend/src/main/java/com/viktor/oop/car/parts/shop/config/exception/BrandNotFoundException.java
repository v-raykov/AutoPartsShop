package com.viktor.oop.car.parts.shop.config.exception;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String message) {
        super(String.format("Brand not found with id: %s", message));
    }
}
