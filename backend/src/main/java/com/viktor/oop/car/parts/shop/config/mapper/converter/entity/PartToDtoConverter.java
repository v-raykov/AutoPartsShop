package com.viktor.oop.car.parts.shop.config.mapper.converter.entity;

import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.stream.Collectors;

public class PartToDtoConverter implements Converter<Part, PartDto> {
    @Override
    public PartDto convert(MappingContext<Part, PartDto> mappingContext) {
        var source = mappingContext.getSource();
        var cars = source.getCars();
        var carIds = cars == null ? null : cars.stream()
                .map(Car::getId)
                .collect(Collectors.toSet());
        return new PartDto(source.getId(), source.getName(), source.getCategory(), source.getPriceBuy(), source.getPriceSell(), source.getQuantity(), carIds);
    }
}
