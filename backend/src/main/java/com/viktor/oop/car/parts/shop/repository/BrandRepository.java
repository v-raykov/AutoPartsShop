package com.viktor.oop.car.parts.shop.repository;

import com.viktor.oop.car.parts.shop.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
}
