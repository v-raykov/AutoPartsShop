package com.viktor.oop.car.parts.shop.repository;

import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findAllByBrand(Brand brand);

    List<Car> findAllByManufacturer(Manufacturer manufacturer);

    List<Car> findAllByManufacturerId(UUID manufacturerId);

    List<Car> findAllByBrandId(UUID brandId);

    List<Car> findAllByPartsId(UUID partId);
}
