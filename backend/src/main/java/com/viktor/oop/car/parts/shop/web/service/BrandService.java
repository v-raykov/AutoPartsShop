package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.config.exception.BrandNotFoundException;
import com.viktor.oop.car.parts.shop.model.dto.BrandDto;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.event.deletion.BrandDeletionEvent;
import com.viktor.oop.car.parts.shop.model.event.deletion.CarDeletionEvent;
import com.viktor.oop.car.parts.shop.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ApplicationEventPublisher eventPublisher;
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

    @Transactional
    public void deleteBrandById(UUID id) {
        var brand = getBrandById(id);
        eventPublisher.publishEvent(new BrandDeletionEvent(brand));
        brandRepository.delete(brand);
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
