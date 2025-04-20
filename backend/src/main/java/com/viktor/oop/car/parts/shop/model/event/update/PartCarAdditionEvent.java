package com.viktor.oop.car.parts.shop.model.event.update;

import com.viktor.oop.car.parts.shop.model.entity.Part;

import java.util.UUID;

public record PartCarAdditionEvent(Part part, UUID carId) {
}
