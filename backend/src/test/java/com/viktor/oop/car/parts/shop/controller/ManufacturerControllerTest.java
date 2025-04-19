package com.viktor.oop.car.parts.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.car.parts.shop.data.TestDataConfig;
import com.viktor.oop.car.parts.shop.data.TestDataContext;
import com.viktor.oop.car.parts.shop.model.dto.ManufacturerDto;
import com.viktor.oop.car.parts.shop.model.dto.update.ManufacturerUpdateDto;
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
class ManufacturerControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TestDataContext testData;

    @Test
    @Order(2)
    void createManufacturer() throws Exception {
        ManufacturerDto manufacturer = new ManufacturerDto();
        manufacturer.setName("TestManufacturer");
        manufacturer.setAddress("123 Test St");
        manufacturer.setPhoneNumber("555-1234");
        manufacturer.setEmail("manufacturer@test.com");

        MvcResult result = mockMvc.perform(post("/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manufacturer)))
                .andExpect(status().isCreated())
                .andReturn();

        testData.manufacturerId = objectMapper.readValue(result.getResponse().getContentAsString(), ManufacturerDto.class).getId();
    }

    @Test
    @Order(3)
    void updateManufacturer() throws Exception {
        ManufacturerUpdateDto updateDto = new ManufacturerUpdateDto();
        updateDto.setName("Updated Manufacturer");
        updateDto.setAddress("456 Update Blvd");
        updateDto.setPhoneNumber("999-888-7777");
        updateDto.setEmail("updated@test.com");

        mockMvc.perform(put("/manufacturer/id/" + testData.manufacturerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk());

        // Fetch and verify
        MvcResult result = mockMvc.perform(get("/manufacturer/id/" + testData.manufacturerId))
                .andExpect(status().isOk())
                .andReturn();

        ManufacturerDto updated = objectMapper.readValue(result.getResponse().getContentAsString(), ManufacturerDto.class);
        assertEquals("Updated Manufacturer", updated.getName());
        assertEquals("456 Update Blvd", updated.getAddress());
        assertEquals("999-888-7777", updated.getPhoneNumber());
        assertEquals("updated@test.com", updated.getEmail());
    }

}
