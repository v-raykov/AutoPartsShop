package com.viktor.oop.car.parts.shop.model.dto.update;

import com.viktor.oop.car.parts.shop.model.PartCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartUpdateDto {
    private String name;
    private PartCategory category;
    private Integer priceBuy;
    private Integer priceSell;
}
