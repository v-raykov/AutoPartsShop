package com.viktor.oop.car.parts.shop.model.dto;

import com.viktor.oop.car.parts.shop.model.PartCategory;
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
public class PartDto {
    private UUID id;
    private String name;
    private PartCategory category;
    private int priceBuy;
    private int priceSell;
    private int quantity;
    private Set<UUID> carIds;
}
