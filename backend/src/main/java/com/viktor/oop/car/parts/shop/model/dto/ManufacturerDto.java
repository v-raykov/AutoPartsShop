package com.viktor.oop.car.parts.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDto {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Set<UUID> carIds;
}
