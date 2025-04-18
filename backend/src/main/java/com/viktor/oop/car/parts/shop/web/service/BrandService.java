package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.BrandNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.BrandDto;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.event.CarCreationEvent;
import com.viktor.oop.car.parts.shop.model.event.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public List<BrandDto> getAllBrandDtos() {
        return brandRepository.findAll().stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .toList();
    }

    public BrandDto getBrandDtoById(UUID id) {
        return modelMapper.map(getBrandById(id), BrandDto.class);
    }

    public BrandDto addBrand(BrandDto brandDto) {
        return modelMapper.map(brandRepository.save(modelMapper.map(brandDto, Brand.class)), BrandDto.class);
    }

    public void deleteBrandById(UUID id) {
        brandRepository.deleteById(id);
    }

    @EventListener
    public void onCarCreationEvent(CarCreationEvent event) {
        var brand = event.brand();
        brand.addCar(event.car());
        brandRepository.save(brand);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onCarDeletionEvent(CarDeletionEvent event) {
        var car = event.car();
        brandRepository.findByCarsId(car.getId()).forEach(brand -> {
            brand.removeCar(car);
            brandRepository.save(brand);
        });
    }

    private Brand getBrandById(UUID id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id.toString()));
    }
}
