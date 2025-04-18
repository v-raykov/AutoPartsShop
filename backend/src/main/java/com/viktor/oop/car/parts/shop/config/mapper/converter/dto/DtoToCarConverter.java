package com.viktor.oop.car.parts.shop.config.mapper.converter.dto;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class DtoToCarConverter implements Converter<CarDto, Car> {
    private final Function<UUID, Manufacturer> manufacturerRetriever;
    private final Function<UUID, Brand> brandRetriever;
    private final Function<Set<UUID>, Set<Part>> partRetriever;

    @Override
    public Car convert(MappingContext<CarDto, Car> mappingContext) {
        var source = mappingContext.getSource();
        var manufacturer = manufacturerRetriever.apply(source.getManufacturerId());
        var brand = brandRetriever.apply(source.getBrandId());
        var partIds = source.getPartIds();
        var parts = partIds == null ? null : partRetriever.apply(source.getPartIds());
        return new Car(null,  manufacturer, brand, parts, source.getModel(), source.getYear());
    }
}
