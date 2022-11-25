package com.customer.service.controller;

import com.customer.service.CustomUtils;
import com.customer.service.model.dto.common.PatchDto;
import com.customer.service.service.DeviceBuilder;
import com.customer.service.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = DeviceController.class)
class DeviceControllerTest {

    @MockBean
    private DeviceService underTestService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/device/{uuid}", DeviceBuilder.getDeviceDto().id())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/device/{uuid}", DeviceBuilder.getDeviceDto().id())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception {

        Mockito.when(underTestService.updateDeviceStatus(
                any(), any(PatchDto.Request.updateStatus.class)))
                        .thenReturn(DeviceBuilder.getUpdateDeviceDto());
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/device/{uuid}/stato", DeviceBuilder.getDeviceDto().id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(DeviceBuilder.getPatchRequestDto()))
                ).andDo(print()).andExpect(MockMvcResultMatchers.content()
                        .string(CustomUtils.asJsonString(DeviceBuilder.getUpdateDeviceDto())))
                .andExpect(status().isCreated());
    }


}