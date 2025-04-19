package com.viktor.oop.car.parts.shop.web.service.helper;

import com.viktor.oop.car.parts.shop.config.exception.BrandNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.CarNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.ManufacturerNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.PartNotFoundException;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import com.viktor.oop.car.parts.shop.repository.BrandRepository;
import com.viktor.oop.car.parts.shop.repository.CarRepository;
import com.viktor.oop.car.parts.shop.repository.ManufacturerRepository;
import com.viktor.oop.car.parts.shop.repository.PartRepository;
import com.viktor.oop.car.parts.shop.web.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdBasedEntityRetriever {
    private final BrandRepository brandRepository;
    private final CarRepository carRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PartRepository partRepository;

    public Brand getBrandById(UUID id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id.toString()));
    }

    public Car getCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id.toString()));
    }

    public Manufacturer getManufacturerById(UUID id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id.toString()));
    }

    public Part getPartById(UUID id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new PartNotFoundException(id.toString()));
    }
}
