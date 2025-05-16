package com.viktor.oop.car.parts.shop.web.controller;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.dto.update.CarUpdateDto;
import com.viktor.oop.car.parts.shop.web.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCarDtos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarDtoById(id));
    }

    @GetMapping("/manufacturer/{manufacturerId}")
    public ResponseEntity<List<CarDto>> getCarsByManufacturerId(@PathVariable UUID manufacturerId) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarsByManufacturerId(manufacturerId));
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarDto>> getCarsByBrandId(@PathVariable UUID brandId) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarsByBrandId(brandId));
    }

    @GetMapping("/part/{partId}")
    public ResponseEntity<List<CarDto>> getCarsByPartId(@PathVariable UUID partId) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarsByPartId(partId));
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCar(carDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable UUID id) {
        carService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable UUID id, @RequestBody CarUpdateDto carDto) {
        carService.updateCar(id, carDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
