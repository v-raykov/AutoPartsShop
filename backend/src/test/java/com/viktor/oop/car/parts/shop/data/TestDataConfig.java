package com.viktor.oop.car.parts.shop.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDataConfig {
    @Bean
    public TestDataContext testDataContext() {
        return new TestDataContext();
    }
}