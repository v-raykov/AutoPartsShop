package com.viktor.oop.car.parts.shop.config.exception;

public class PartNotFoundException extends RuntimeException {
    public PartNotFoundException(String message) {
        super(String.format("Part not found with id: %s", message));
    }
}
