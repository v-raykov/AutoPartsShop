package com.viktor.oop.car.parts.shop.web.controller;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
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

    @PostMapping
    public ResponseEntity<CarDto> addCar(@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCar(carDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable UUID id) {
        carService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/id/{id}/manufacturer")
    public ResponseEntity<Void> updateCarManufacturer(@PathVariable UUID id, @RequestParam UUID manufacturerId) {
        carService.updateCarManufacturer(id, manufacturerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
