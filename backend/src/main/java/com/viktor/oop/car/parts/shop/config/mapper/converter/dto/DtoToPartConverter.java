package com.viktor.oop.car.parts.shop.config.mapper.converter.dto;

import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class DtoToPartConverter implements Converter<PartDto, Part> {
    private final Function<Set<UUID>, Set<Car>> carRetriever;

    @Override
    public Part convert(MappingContext<PartDto, Part> mappingContext) {
        var source = mappingContext.getSource();
        var carIds = source.getCarIds();
        var cars = carIds == null ? null : carRetriever.apply(carIds);
        return new Part(null, source.getName(), source.getCategory(), source.getPriceBuy(), source.getPriceSell(), source.getQuantity(), cars);
    }
}
