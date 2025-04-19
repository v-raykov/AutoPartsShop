package com.viktor.oop.car.parts.shop.model.entity;

import com.viktor.oop.car.parts.shop.model.PartCategory;
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
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PartCategory category;

    private int priceBuy;

    private int priceSell;

    private int quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "part_car",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars;

    public void changeQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }
}
