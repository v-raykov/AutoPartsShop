package com.viktor.oop.car.parts.shop.model.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartUpdateDto {
    private Integer priceBuy;
    private Integer priceSell;
}
