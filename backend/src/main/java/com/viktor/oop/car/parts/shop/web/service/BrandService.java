package com.viktor.oop.car.parts.shop.web.service;

import com.viktor.oop.car.parts.shop.model.dto.BrandDto;
import com.viktor.oop.car.parts.shop.model.entity.Brand;
import com.viktor.oop.car.parts.shop.model.entity.Car;
import com.viktor.oop.car.parts.shop.repository.BrandRepository;
import com.viktor.oop.car.parts.shop.web.service.helper.IdBasedEntityRetriever;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final IdBasedEntityRetriever retriever;
    private final ModelMapper modelMapper;

    public List<BrandDto> getAllBrandDtos() {
        return brandRepository.findAll().stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .toList();
    }

    public BrandDto getBrandDtoById(UUID id) {
        return modelMapper.map(retriever.getBrandById(id), BrandDto.class);
    }

    public BrandDto addBrand(BrandDto brandDto) {
        return modelMapper.map(brandRepository.save(modelMapper.map(brandDto, Brand.class)), BrandDto.class);
    }

    public void deleteBrandById(UUID id) {
        brandRepository.deleteById(id);
    }

    protected void addCarToBrand(Car car, Brand brand) {
        brand.add(car);
        brandRepository.save(brand);
    }
}
