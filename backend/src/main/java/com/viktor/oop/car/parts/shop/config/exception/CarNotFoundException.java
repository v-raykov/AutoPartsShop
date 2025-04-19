package com.viktor.oop.car.parts.shop.config.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {
        super(String.format("Car not found with id: %s", message));
    }
}
