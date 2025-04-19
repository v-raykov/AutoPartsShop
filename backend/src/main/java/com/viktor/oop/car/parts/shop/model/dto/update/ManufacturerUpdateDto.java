package com.viktor.oop.car.parts.shop.model.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerUpdateDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
