package com.viktor.oop.car.parts.shop.model.entity;

import com.viktor.oop.car.parts.shop.model.PartCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PartCategory category;

    private int priceBuy;

    private int priceSell;

    private int stock;

    @ManyToMany
    private List<Car> cars;
}
