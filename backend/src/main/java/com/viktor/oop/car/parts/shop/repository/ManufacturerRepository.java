package com.viktor.oop.car.parts.shop.repository;

import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
}
