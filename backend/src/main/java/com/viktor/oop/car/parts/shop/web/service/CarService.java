package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.CarNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.model.event.AddCarToPartEvent;
import com.viktor.oop.car.parts.shop.model.event.CarCreationEvent;
import com.viktor.oop.car.parts.shop.model.event.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

    @Transactional
    public CarDto addCar(CarDto carDto) {
        var car = modelMapper.map(carDto, Car.class);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> eventPublisher.publishEvent(new CarCreationEvent(car, car.getBrand(), car.getManufacturer())));
        future.join();
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    @Transactional
    public void deleteCarById(UUID id) {
        var car = getCarById(id);
        eventPublisher.publishEvent(new CarDeletionEvent(car));
        carRepository.delete(car);
    }

    @EventListener
    public void onAddCarToPartEvent(AddCarToPartEvent event) {
        var car = getCarById(event.carId());
        event.part().addCar(car);
    }

    private Car getCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id.toString()));
    }
}
