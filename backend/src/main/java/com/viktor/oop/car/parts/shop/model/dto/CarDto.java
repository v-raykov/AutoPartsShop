package com.viktor.oop.car.parts.shop.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private UUID id;
    @NotNull(message = "Field 'manufacturerId' is required")
    private UUID manufacturerId;
    @NotNull(message = "Field 'brandId' is required")
    private UUID brandId;
    private String model;
    private int year;
}
