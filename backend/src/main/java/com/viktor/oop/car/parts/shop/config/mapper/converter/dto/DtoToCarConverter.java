package com.viktor.oop.car.parts.shop.config.mapper.converter.dto;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.web.service.BrandService;
import com.viktor.oop.car.parts.shop.web.service.ManufacturerService;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

@RequiredArgsConstructor
public class DtoToCarConverter implements Converter<CarDto, Car> {
    private final IdBasedEntityRetriever retriever;

    @Override
    public Car convert(MappingContext<CarDto, Car> mappingContext) {
        var source = mappingContext.getSource();
        var manufacturer = retriever.getManufacturerById(source.getManufacturerId());
        var brand = retriever.getBrandById(source.getBrandId());
        return new Car(null,  manufacturer, brand, source.getModel(), source.getYear());
    }
}
