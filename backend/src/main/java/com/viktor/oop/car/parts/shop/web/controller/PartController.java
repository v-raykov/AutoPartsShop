package com.viktor.oop.car.parts.shop.web.controller;

import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.dto.update.PartUpdateDto;
import com.viktor.oop.car.parts.shop.web.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/part")
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;

    @GetMapping
    public ResponseEntity<List<PartDto>> getAllParts() {
        return ResponseEntity.status(HttpStatus.OK).body(partService.getAllPartDtos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PartDto> getPartById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(partService.getPartDtoById(id));
    }

    @PostMapping
    public ResponseEntity<PartDto> addPart(@RequestBody PartDto partDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.addPart(partDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletePartById(@PathVariable UUID id) {
        partService.deletePartById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/id/{id}/car")
    public ResponseEntity<Void> addCarToPart(@PathVariable UUID id, @RequestParam UUID carId) {
        partService.addCarToPart(id, carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/id/{id}/car")
    public ResponseEntity<Void> deleteCarFromPart(@PathVariable UUID id, @RequestParam UUID carId) {
        partService.deleteCarFromPart(id, carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/id/{id}/quantity")
    public ResponseEntity<Void> updatePartQuantity(@PathVariable UUID id, @RequestParam int quantity) {
        partService.updatePartQuantity(id, quantity);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> updatePart(@PathVariable UUID id, @RequestBody PartUpdateDto dto) {
        partService.updatePart(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
