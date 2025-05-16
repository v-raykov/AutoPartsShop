package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.CarNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.dto.update.CarUpdateDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.event.creation.CarCreationEvent;
import com.viktor.oop.car.parts.shop.model.event.creation.PartCreationEvent;
import com.viktor.oop.car.parts.shop.model.event.deletion.BrandDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.deletion.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.deletion.ManufacturerDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.update.PartCarAdditionEvent;
import com.viktor.oop.car.parts.shop.model.event.update.PartCarDeletionEvent;
import com.viktor.oop.car.parts.shop.repository.CarRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.UUID;

import static com.viktor.oop.car.parts.shop.web.service.helper.Utilities.updateEntity;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ModelMapper modelMapper;

    public List<CarDto> getAllCarDtos() {
        return carRepository.findAll().stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
    }

    public CarDto getCarDtoById(UUID id) {
        return modelMapper.map(getCarById(id), CarDto.class);
    }

    public List<CarDto> getCarsByManufacturerId(UUID manufacturerId) {
        return carRepository.findAllByManufacturerId(manufacturerId).stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
    }

    public List<CarDto> getCarsByBrandId(UUID brandId) {
        return carRepository.findAllByBrandId(brandId).stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
    }

    public List<CarDto> getCarsByPartId(UUID partId) {
        return carRepository.findAllByPartsId(partId).stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
    }

    public CarDto addCar(CarDto carDto) {
        var car = carRepository.save(modelMapper.map(carDto, Car.class));
        var parts = car.getParts();
        if (parts != null) {
            eventPublisher.publishEvent(new CarCreationEvent(car, parts));
        }
        return modelMapper.map(car, CarDto.class);
    }

    @Transactional
    public void deleteCarById(UUID id) {
        var car = getCarById(id);
        eventPublisher.publishEvent(new CarDeletionEvent(car));
        carRepository.delete(car);
    }

    @EventListener
    public void onPartCreationEvent(PartCreationEvent event) {
        var part = event.part();
        var cars = part.getCars();
        cars.forEach(car -> car.addPart(part));
        carRepository.saveAll(cars);
    }

    @EventListener
    public void onAddCarToPartEvent(PartCarAdditionEvent event) {
        var car = getCarById(event.carId());
        var part = event.part();
        part.addCar(car);
        car.addPart(part);
        carRepository.save(car);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPartCarDeletionEvent(PartCarDeletionEvent event) {
        var part = event.part();
        var car = getCarById(event.carId());
        part.removeCar(car);
        car.removePart(part);
        carRepository.save(car);
    }

    @Transactional
    @EventListener
    public void onBrandDeletionEvent(BrandDeletionEvent event) {
        var cars = carRepository.findAllByBrand(event.brand());
        cars.forEach(car -> deleteCarById(car.getId()));
    }

    @Transactional
    @EventListener
    public void onManufacturerDeletionEvent(ManufacturerDeletionEvent event) {
        var cars = carRepository.findAllByManufacturer(event.manufacturer());
        cars.forEach(car -> deleteCarById(car.getId()));
    }

    private Car getCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id.toString()));
    }

    public void updateCar(UUID id, CarUpdateDto carDto) {
        var car = getCarById(id);
        updateEntity(carDto, car);
        carRepository.save(car);
    }
}
