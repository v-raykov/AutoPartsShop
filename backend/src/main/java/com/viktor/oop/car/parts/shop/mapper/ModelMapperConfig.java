package com.viktor.oop.car.parts.shop.mapper;

import com.viktor.oop.car.parts.shop.mapper.converter.BrandToDtoConverter;
import com.viktor.oop.car.parts.shop.mapper.converter.CarToDtoConverter;
import com.viktor.oop.car.parts.shop.mapper.converter.ManufacturerToDtoConverter;
import com.viktor.oop.car.parts.shop.mapper.converter.PartToDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        modelMapper.addConverter(new BrandToDtoConverter());
        modelMapper.addConverter(new CarToDtoConverter());
        modelMapper.addConverter(new ManufacturerToDtoConverter());
        modelMapper.addConverter(new PartToDtoConverter());
        return modelMapper;
    }
}
