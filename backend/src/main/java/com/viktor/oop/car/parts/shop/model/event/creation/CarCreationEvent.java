package com.viktor.oop.car.parts.shop.model.event.creation;

import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import org.springframework.context.ApplicationEvent;

import java.util.Set;

public record CarCreationEvent(Car car, Set<Part> parts) {
}
