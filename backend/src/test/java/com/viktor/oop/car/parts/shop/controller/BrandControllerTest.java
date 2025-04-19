package com.viktor.oop.car.parts.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.car.parts.shop.data.TestDataConfig;
import com.viktor.oop.car.parts.shop.data.TestDataContext;
import com.viktor.oop.car.parts.shop.model.dto.BrandDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDataConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BrandControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TestDataContext testData;

    @Test
    @Order(1)
    void createBrand() throws Exception {
        BrandDto brand = new BrandDto();
        brand.setName("TestBrand");

        MvcResult result = mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brand)))
                .andExpect(status().isCreated())
                .andReturn();

        testData.brandId = objectMapper.readValue(result.getResponse().getContentAsString(), BrandDto.class).getId();
    }
}
