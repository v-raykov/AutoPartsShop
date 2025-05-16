package com.viktor.oop.car.parts.shop.model.dto.update;

import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;

public class CarUpdateDto {
    private Manufacturer manufacturer;
    private Brand brand;
    private String model;
    private Integer year;
}
