package com.viktor.oop.car.parts.shop.config.mapper.converter.entity;

import com.viktor.oop.car.parts.shop.model.dto.ManufacturerDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.stream.Collectors;

public class ManufacturerToDtoConverter implements Converter<Manufacturer, ManufacturerDto> {
    @Override
    public ManufacturerDto convert(MappingContext<Manufacturer, ManufacturerDto> mappingContext) {
        var source = mappingContext.getSource();
        var cars = source.getCars();
        var carIds = cars == null ? null : cars.stream()
                .map(Car::getId)
                .collect(Collectors.toSet());
        return new ManufacturerDto(source.getId(), source.getName(), source.getAddress(), source.getPhoneNumber(), source.getEmail(), carIds);
    }
}
