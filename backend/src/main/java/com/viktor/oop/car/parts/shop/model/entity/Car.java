package com.viktor.oop.car.parts.shop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Brand brand;

    @ManyToMany(mappedBy = "cars", fetch = FetchType.EAGER)
    private Set<Part> parts;

    private String model;

    private int year;

    public void addPart(Part part) {
        parts.add(part);
    }

    public void removePart(Part part) {
        parts.remove(part);
    }
}
