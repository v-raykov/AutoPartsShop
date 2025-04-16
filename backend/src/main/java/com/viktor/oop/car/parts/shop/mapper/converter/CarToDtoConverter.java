package com.viktor.oop.car.parts.shop.mapper.converter;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CarToDtoConverter implements Converter<Car, CarDto> {
    @Override
    public CarDto convert(MappingContext<Car, CarDto> mappingContext) {
        var source = mappingContext.getSource();

        return new CarDto(source.getId(), source.getManufacturer().getId(), source.getBrand().getId(), source.getModel(), source.getYear());
    }
}
