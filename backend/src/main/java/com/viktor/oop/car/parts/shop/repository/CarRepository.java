package com.viktor.oop.car.parts.shop.repository;

import com.viktor.oop.car.parts.shop.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    Car findCarById(UUID id);
}
