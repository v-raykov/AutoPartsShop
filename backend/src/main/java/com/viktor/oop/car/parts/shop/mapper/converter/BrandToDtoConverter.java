package com.viktor.oop.car.parts.shop.mapper.converter;

import com.viktor.oop.car.parts.shop.model.dto.BrandDto;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.stream.Collectors;

public class BrandToDtoConverter implements Converter<Brand, BrandDto> {
    @Override
    public BrandDto convert(MappingContext<Brand, BrandDto> mappingContext) {
        var source = mappingContext.getSource();
        var carIds = source.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toSet());
        return new BrandDto(source.getId(), source.getName(), carIds);
    }
}
