package com.viktor.oop.car.parts.shop.model.event;

import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;

public record CarCreationEvent(Car car, Brand brand, Manufacturer manufacturer) {
}
