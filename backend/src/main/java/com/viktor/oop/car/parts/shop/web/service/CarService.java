package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.repository.BrandRepository;
import com.viktor.oop.car.parts.shop.repository.CarRepository;
import com.viktor.oop.car.parts.shop.repository.ManufacturerRepository;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BrandService brandService;
    private final ManufacturerService manufacturerService;
    private final IdBasedEntityRetriever retriever;
    private final ModelMapper modelMapper;

    public List<CarDto> getAllCarDtos() {
        return carRepository.findAll().stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
    }

    public CarDto getCarDtoById(UUID id) {
        return modelMapper.map(retriever.getCarById(id), CarDto.class);
    }

    @Transactional
    public CarDto addCar(CarDto carDto) {
        var car = modelMapper.map(carDto, Car.class);
        brandService.addCarToBrand(car, car.getBrand());
        manufacturerService.addCarToManufacturer(car, car.getManufacturer());
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    public void deleteCarById(UUID id) {
        carRepository.deleteById(id);
        System.out.println(carRepository.existsById(id));
    }

    public void updateCarManufacturer(UUID id, UUID manufacturer) {
        var car = retriever.getCarById(id);
        car.setManufacturer(retriever.getManufacturerById(manufacturer));
        carRepository.save(car);
    }
}
