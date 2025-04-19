package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.model.dto.ManufacturerDto;
import com.viktor.oop.car.parts.shop.model.dto.update.ManufacturerUpdateDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import com.viktor.oop.car.parts.shop.repository.ManufacturerRepository;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.viktor.oop.car.parts.shop.web.service.helper.Utilities.updateEntity;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final IdBasedEntityRetriever retriever;
    private final ModelMapper modelMapper;

    public List<ManufacturerDto> getAllManufacturerDtos() {
        return manufacturerRepository.findAll().stream()
                .map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDto.class))
                .toList();
    }

    public ManufacturerDto getManufacturerDtoById(UUID id) {
        return modelMapper.map(retriever.getManufacturerById(id), ManufacturerDto.class);
    }

    public ManufacturerDto addManufacturer(ManufacturerDto dto) {
        return modelMapper.map(manufacturerRepository.save(modelMapper.map(dto, Manufacturer.class)), ManufacturerDto.class);
    }

    public void deleteManufacturerById(UUID id) {
        manufacturerRepository.deleteById(id);
    }

    public void updateManufacturer(UUID id, ManufacturerUpdateDto dto) {
        var manufacturer = retriever.getManufacturerById(id);
        updateEntity(dto, manufacturer);
        manufacturerRepository.save(manufacturer);
    }

    public void addCarToManufacturer(Car car, Manufacturer manufacturer) {
        car.getManufacturer().add(car);
        manufacturerRepository.save(car.getManufacturer());
    }
}
