package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.ManufacturerNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.ManufacturerDto;
import com.viktor.oop.car.parts.shop.model.dto.update.ManufacturerUpdateDto;
import com.viktor.oop.car.parts.shop.model.entity.Manufacturer;
import com.viktor.oop.car.parts.shop.model.event.CarCreationEvent;
import com.viktor.oop.car.parts.shop.model.event.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.UUID;

import static com.viktor.oop.car.parts.shop.web.service.helper.Utilities.updateEntity;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ModelMapper modelMapper;

    public List<ManufacturerDto> getAllManufacturerDtos() {
        return manufacturerRepository.findAll().stream()
                .map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDto.class))
                .toList();
    }

    public ManufacturerDto getManufacturerDtoById(UUID id) {
        return modelMapper.map(getManufacturerById(id), ManufacturerDto.class);
    }

    public ManufacturerDto addManufacturer(ManufacturerDto dto) {
        return modelMapper.map(manufacturerRepository.save(modelMapper.map(dto, Manufacturer.class)), ManufacturerDto.class);
    }

    public void deleteManufacturerById(UUID id) {
        manufacturerRepository.deleteById(id);
    }

    public void updateManufacturer(UUID id, ManufacturerUpdateDto dto) {
        var manufacturer = getManufacturerById(id);
        updateEntity(dto, manufacturer);
        manufacturerRepository.save(manufacturer);
    }

    @EventListener
    public void onCarCreationEvent(CarCreationEvent event) {
        var manufacturer = event.manufacturer();
        manufacturer.addCar(event.car());
        manufacturerRepository.save(manufacturer);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onCarDeletionEvent(CarDeletionEvent event) {
        var car = event.car();
        manufacturerRepository.findByCarsId(car.getId()).forEach(manufacturer -> {
            manufacturer.removeCar(car);
            manufacturerRepository.save(manufacturer);
        });
    }


    private Manufacturer getManufacturerById(UUID id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id.toString()));
    }
}
