package com.viktor.oop.car.parts.shop.config.mapper.converter.entity;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.stream.Collectors;

public class CarToDtoConverter implements Converter<Car, CarDto> {
    @Override
    public CarDto convert(MappingContext<Car, CarDto> mappingContext) {
        var source = mappingContext.getSource();
        var parts = source.getParts();
        var partIds = parts == null ? null : parts.stream().map(Part::getId).collect(Collectors.toSet());
        return new CarDto(source.getId(), source.getManufacturer().getId(), source.getBrand().getId(), partIds, source.getModel(), source.getYear());
    }
}
