package com.viktor.oop.car.parts.shop.config.mapper.converter.dto;

import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import com.viktor.oop.car.parts.shop.web.service.CarService;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DtoToPartConverter implements Converter<PartDto, Part> {
    private final IdBasedEntityRetriever retriever;
    @Override
    public Part convert(MappingContext<PartDto, Part> mappingContext) {
        var source = mappingContext.getSource();
        var cars = source.getCarIds().stream()
                .map(retriever::getCarById)
                .collect(Collectors.toSet());
        return new Part(null, source.getName(), source.getCategory(), source.getPriceBuy(), source.getPriceSell(), source.getQuantity(), cars);
    }
}
