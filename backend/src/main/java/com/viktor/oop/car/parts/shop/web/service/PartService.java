package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.PartNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.dto.update.PartUpdateDto;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import com.viktor.oop.car.parts.shop.model.event.update.PartCarAdditionEvent;
import com.viktor.oop.car.parts.shop.model.event.deletion.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.update.PartCarDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.creation.PartCreationEvent;
import com.viktor.oop.car.parts.shop.repository.PartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.viktor.oop.car.parts.shop.web.service.helper.Utilities.updateEntity;

@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ModelMapper modelMapper;

    public List<PartDto> getAllPartDtos() {
        return partRepository.findAll().stream()
                .map(part -> modelMapper.map(part, PartDto.class))
                .toList();
    }

    public PartDto getPartDtoById(UUID id) {
        return modelMapper.map(getPartById(id), PartDto.class);
    }

    public void updatePartQuantity(UUID id, int quantity) {
        var part = getPartById(id);
        part.changeQuantity(quantity);
        partRepository.save(part);
    }

    public void deletePartById(UUID id) {
        partRepository.deleteById(id);
    }

    @Transactional
    public PartDto addPart(PartDto partDto) {
        var part = modelMapper.map(partDto, Part.class);
        eventPublisher.publishEvent(new PartCreationEvent(part));
        return modelMapper.map(partRepository.save(part), PartDto.class);
    }

    public void addCarToPart(UUID id, UUID carId) {
        var part = getPartById(id);
        eventPublisher.publishEvent(new PartCarAdditionEvent(part, carId));
        partRepository.save(part);
    }

    @Transactional
    public void deleteCarFromPart(UUID id, UUID carId) {
        var part = getPartById(id);
        eventPublisher.publishEvent(new PartCarDeletionEvent(part, carId));
        partRepository.save(part);
    }

    public void updatePart(UUID id, PartUpdateDto dto) {
        var part = getPartById(id);
        updateEntity(dto, part);
        partRepository.save(part);
    }

    @EventListener
    public void onCarDeletionEvent(CarDeletionEvent event) {
        var car = event.car();
        var parts = car.getParts();
        parts.forEach(part -> part.removeCar(car));
        partRepository.saveAll(parts);
    }

    private Part getPartById(UUID id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new PartNotFoundException(id.toString()));
    }
}
