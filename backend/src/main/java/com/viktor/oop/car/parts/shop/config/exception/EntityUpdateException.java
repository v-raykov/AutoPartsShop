package com.viktor.oop.car.parts.shop.config.exception;

public class EntityUpdateException extends RuntimeException {
    public EntityUpdateException(Exception ex) {
        super(ex);
    }
}
