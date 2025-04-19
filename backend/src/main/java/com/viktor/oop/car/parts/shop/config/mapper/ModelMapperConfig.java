package com.viktor.oop.car.parts.shop.config.mapper;

import com.viktor.oop.car.parts.shop.config.exception.BrandNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.ManufacturerNotFoundException;
import com.viktor.oop.car.parts.shop.config.mapper.converter.dto.DtoToCarConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.dto.DtoToPartConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.BrandToDtoConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.CarToDtoConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.ManufacturerToDtoConverter;
import com.viktor.oop.car.parts.shop.config.mapper.converter.entity.PartToDtoConverter;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import com.viktor.oop.car.parts.shop.repository.BrandRepository;
import com.viktor.oop.car.parts.shop.repository.CarRepository;
import com.viktor.oop.car.parts.shop.repository.ManufacturerRepository;
import com.viktor.oop.car.parts.shop.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();
    private final BrandRepository brandRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;

    @Bean
    public ModelMapper modelMapper() {
        modelMapper.addConverter(new BrandToDtoConverter());
        modelMapper.addConverter(new CarToDtoConverter());
        modelMapper.addConverter(new ManufacturerToDtoConverter());
        modelMapper.addConverter(new PartToDtoConverter());

        modelMapper.addConverter(new DtoToPartConverter(this::getCarsByIds));
        modelMapper.addConverter(new DtoToCarConverter(this::getManufacturerById, this::getBrandById, this::getPartsByIds));
        return modelMapper;
    }

    private Brand getBrandById(UUID id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id.toString()));
    }

    private Manufacturer getManufacturerById(UUID id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id.toString()));
    }

    private Set<Car> getCarsByIds(Set<UUID> ids) {
        return new HashSet<>(carRepository.findAllById(ids));
    }

    private Set<Part> getPartsByIds(Set<UUID> ids) {
        return new HashSet<>(partRepository.findAllById(ids));
    }
}
