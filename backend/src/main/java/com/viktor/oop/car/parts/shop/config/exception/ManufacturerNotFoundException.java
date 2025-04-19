package com.viktor.oop.car.parts.shop.config.exception;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(String message) {
        super(String.format("Manufacturer not found with id: %s", message));
    }
}
