package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.dto.update.PartUpdateDto;
import com.viktor.oop.car.parts.shop.model.entity.Part;
import com.viktor.oop.car.parts.shop.repository.PartRepository;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.viktor.oop.car.parts.shop.web.service.helper.Utilities.updateEntity;

@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;
    private final IdBasedEntityRetriever retriever;
    private final ModelMapper modelMapper;

    public List<PartDto> getAllPartDtos() {
        return partRepository.findAll().stream()
                .map(part -> modelMapper.map(part, PartDto.class))
                .toList();
    }

    public PartDto getPartDtoById(UUID id) {
        return modelMapper.map(retriever.getPartById(id), PartDto.class);
    }

    public void updatePartQuantity(UUID id, int quantity) {
        var part = retriever.getPartById(id);
        part.changeQuantity(quantity);
        partRepository.save(part);
    }

    public void deletePartById(UUID id) {
        partRepository.deleteById(id);
    }

    public PartDto addPart(PartDto partDto) {
        return modelMapper.map(partRepository.save(modelMapper.map(partDto, Part.class)), PartDto.class);
    }

    public void addCarToPart(UUID id, UUID carId) {
        var part = retriever.getPartById(id);
        part.addCar(retriever.getCarById(carId));
        partRepository.save(part);
    }

    public void deleteCarFromPart(UUID id, UUID carId) {
        var part = retriever.getPartById(id);
        part.removeCar(retriever.getCarById(carId));
        partRepository.save(part);
    }

    public void updatePart(UUID id, PartUpdateDto dto) {
        var part = retriever.getPartById(id);
        updateEntity(dto, part);
        partRepository.save(part);
    }
}
