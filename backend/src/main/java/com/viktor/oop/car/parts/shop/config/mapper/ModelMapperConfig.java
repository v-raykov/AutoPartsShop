package com.viktor.oop.car.parts.shop.config.mapper;

import com.viktor.oop.car.parts.shop.config.mapper.converter.dto.DtoToCarConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.dto.DtoToPartConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.BrandToDtoConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.CarToDtoConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.ManufacturerToDtoConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.PartToDtoConverter;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();

    private final IdBasedEntityRetriever retriever;
    @Bean
    public ModelMapper modelMapper() {
        modelMapper.addConverter(new BrandToDtoConverter());
        modelMapper.addConverter(new CarToDtoConverter());
        modelMapper.addConverter(new ManufacturerToDtoConverter());
        modelMapper.addConverter(new PartToDtoConverter());

        modelMapper.addConverter(new DtoToPartConverter(retriever));
        modelMapper.addConverter(new DtoToCarConverter(retriever));
        return modelMapper;
    }
}
