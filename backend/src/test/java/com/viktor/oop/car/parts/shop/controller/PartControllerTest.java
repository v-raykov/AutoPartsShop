package com.viktor.oop.car.parts.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.car.parts.shop.data.TestDataConfig;
import com.viktor.oop.car.parts.shop.data.TestDataContext;
import com.viktor.oop.car.parts.shop.model.PartCategory;
import com.viktor.oop.car.parts.shop.model.dto.PartDto;
import com.viktor.oop.car.parts.shop.model.dto.update.PartUpdateDto;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDataConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PartControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TestDataContext testData;

    @Test
    @Order(6)
    void createPartAndAttachToCar() throws Exception {
        PartDto part = new PartDto();
        part.setName("TestPart");
        part.setCategory(PartCategory.ENGINE);
        part.setPriceBuy(100);
        part.setPriceSell(150);
        part.setQuantity(5);

        MvcResult result = mockMvc.perform(post("/part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(part)))
                .andExpect(status().isCreated())
                .andReturn();

        testData.partId = objectMapper.readValue(result.getResponse().getContentAsString(), PartDto.class).getId();

        mockMvc.perform(post("/part/id/" + testData.partId + "/car")
                        .param("carId", testData.carId.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    void verifyPartRelationshipToCar() throws Exception {
        MvcResult result = mockMvc.perform(get("/part/id/" + testData.partId))
                .andExpect(status().isOk())
                .andReturn();

        PartDto part = objectMapper.readValue(result.getResponse().getContentAsString(), PartDto.class);
        assertTrue(part.getCarIds().contains(testData.carId));
    }


    @Test
    @Order(8)
    void updatePart() throws Exception {
        PartUpdateDto updateDto = new PartUpdateDto();
        updateDto.setPriceBuy(200);
        updateDto.setPriceSell(300);

        mockMvc.perform(put("/part/id/" + testData.partId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk());

        // Verify the update
        MvcResult result = mockMvc.perform(get("/part/id/" + testData.partId))
                .andExpect(status().isOk())
                .andReturn();

        PartDto updated = objectMapper.readValue(result.getResponse().getContentAsString(), PartDto.class);
        assertEquals(200, updated.getPriceBuy());
        assertEquals(300, updated.getPriceSell());
    }

}
