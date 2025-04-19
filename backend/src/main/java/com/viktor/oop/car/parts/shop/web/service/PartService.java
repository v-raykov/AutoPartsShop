package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.PartNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.dto.update.PartUpdateDto;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import com.viktor.oop.car.parts.shop.model.event.AddCarToPartEvent;
import com.viktor.oop.car.parts.shop.model.event.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.PartCreationEvent;
import com.viktor.oop.car.parts.shop.repository.PartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
        eventPublisher.publishEvent(new AddCarToPartEvent(part, carId));
        partRepository.save(part);
    }

    public void deleteCarFromPart(UUID id, UUID carId) {
        var part = getPartById(id);
        //part.removeCar(retriever.getCarById(carId));
        partRepository.save(part);
    }

    public void updatePart(UUID id, PartUpdateDto dto) {
        var part = getPartById(id);
        updateEntity(dto, part);
        partRepository.save(part);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onCarDeletionEvent(CarDeletionEvent event) {
        var car = event.car();
        partRepository.findByCarsId(car.getId()).forEach(part -> {
            part.removeCar(car);
            partRepository.save(part);
        });
    }

    private Part getPartById(UUID id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new PartNotFoundException(id.toString()));
    }
}
