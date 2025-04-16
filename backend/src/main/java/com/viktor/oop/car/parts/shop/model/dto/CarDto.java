package com.viktor.oop.car.parts.shop.model.dto;

import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private UUID id;
    private UUID manufacturerId;
    private UUID brandId;
    private String model;
    private int year;
}
