package com.viktor.oop.car.parts.shop.model.event;

import com.viktor.oop.car.parts.shop.model.entity.Part;

import java.util.UUID;

public record AddCarToPartEvent(Part part, UUID carId) {
}
