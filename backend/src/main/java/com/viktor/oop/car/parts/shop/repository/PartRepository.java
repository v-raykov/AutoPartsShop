package com.viktor.oop.car.parts.shop.repository;

import com.viktor.oop.car.parts.shop.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartRepository  extends JpaRepository<Part, UUID> {
}
