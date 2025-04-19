package com.viktor.oop.car.parts.shop.web.controller;

import com.viktor.oop.car.parts.shop.model.dto.ManufacturerDto;
import com.viktor.oop.car.parts.shop.model.dto.update.ManufacturerUpdateDto;
import com.viktor.oop.car.parts.shop.web.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerDto>> getAllManufacturerDtos() {
        return ResponseEntity.status(HttpStatus.OK).body(manufacturerService.getAllManufacturerDtos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ManufacturerDto> getManufacturerDtoById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(manufacturerService.getManufacturerDtoById(id));
    }

    @PostMapping()
    public ResponseEntity<ManufacturerDto> addManufacturer(@RequestBody ManufacturerDto manufacturer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturerService.addManufacturer(manufacturer));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteManufacturerById(@PathVariable UUID id) {
        manufacturerService.deleteManufacturerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> updateManufacturer(@PathVariable UUID id, @RequestBody ManufacturerUpdateDto dto) {
        manufacturerService.updateManufacturer(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
