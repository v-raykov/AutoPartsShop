package com.viktor.oop.car.parts.shop.model.event;

import com.viktor.oop.car.parts.shop.model.entity.Car;

public record CarDeletionEvent(Car car) {
}
