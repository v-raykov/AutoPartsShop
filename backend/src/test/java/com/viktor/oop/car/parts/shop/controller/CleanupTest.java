package com.viktor.oop.car.parts.shop.controller;

import com.viktor.oop.car.parts.shop.data.TestDataConfig;
import com.viktor.oop.car.parts.shop.data.TestDataContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDataConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CleanupTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private TestDataContext testData;

    @Test
    @Order(9)
    void cleanupTestData() throws Exception {
        if (testData.partId != null)
            mockMvc.perform(delete("/part/id/" + testData.partId)).andExpect(status().isOk());

        if (testData.carId != null)
            mockMvc.perform(delete("/car/id/" + testData.carId)).andExpect(status().isOk());

        if (testData.brandId != null)
            mockMvc.perform(delete("/brand/id/" + testData.brandId)).andExpect(status().isOk());

        if (testData.manufacturerId != null)
            mockMvc.perform(delete("/manufacturer/id/" + testData.manufacturerId)).andExpect(status().isOk());
    }

    @AfterAll
    void clearSharedContext() {
        testData.brandId = null;
        testData.manufacturerId = null;
        testData.carId = null;
        testData.partId = null;
    }
}
