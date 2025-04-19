package com.viktor.oop.car.parts.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.car.parts.shop.data.TestDataConfig;
import com.viktor.oop.car.parts.shop.data.TestDataContext;
import com.viktor.oop.car.parts.shop.model.dto.CarDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDataConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TestDataContext testData;

    @Test
    @Order(4)
    void createCar() throws Exception {
        CarDto car = new CarDto();
        car.setModel("TestModel");
        car.setYear(2025);
        car.setBrandId(testData.brandId);
        car.setManufacturerId(testData.manufacturerId);

        MvcResult result = mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andReturn();

        testData.carId = objectMapper.readValue(result.getResponse().getContentAsString(), CarDto.class).getId();
    }
    @Test
    @Order(5)
    void verifyCarRelationships() throws Exception {
        MvcResult result = mockMvc.perform(get("/car/id/" + testData.carId))
                .andExpect(status().isOk())
                .andReturn();

        CarDto car = objectMapper.readValue(result.getResponse().getContentAsString(), CarDto.class);
        assertEquals(testData.brandId, car.getBrandId());
        assertEquals(testData.manufacturerId, car.getManufacturerId());
    }


}
