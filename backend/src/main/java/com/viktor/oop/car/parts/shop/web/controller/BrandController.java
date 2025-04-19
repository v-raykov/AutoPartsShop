package com.viktor.oop.car.parts.shop.web.controller;

import com.viktor.oop.car.parts.shop.model.dto.BrandDto;
import com.viktor.oop.car.parts.shop.web.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.getAllBrandDtos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.getBrandDtoById(id));
    }

    @PostMapping
    public ResponseEntity<BrandDto> addBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.addBrand(brandDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable UUID id) {
        brandService.deleteBrandById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
