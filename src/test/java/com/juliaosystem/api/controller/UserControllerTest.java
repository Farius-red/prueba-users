package com.juliaosystem.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.infrastructure.services.primary.UserService;
import com.juliaosystem.utils.PlantillaResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddUser() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        // Configurar el DTO según tus necesidades

        PlantillaResponse<RegisterUserDTO> mockResponse = PlantillaResponse.<RegisterUserDTO>builder()
                .httpStatus(HttpStatus.OK)
                .data(registerUserDTO)
                .build();
        when(userService.addUser(any(RegisterUserDTO.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/user/add")
                        .content(asJsonString(registerUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        verify(userService).addUser(any(RegisterUserDTO.class));
    }

    @Test
    void testGetUsers() throws Exception {
        UUID userId = UUID.randomUUID();
        Long businessId = 123L;

        PlantillaResponse<RegisterUserDTO> mockResponse = PlantillaResponse.<RegisterUserDTO>builder()
                .httpStatus(HttpStatus.OK)
                .data(new RegisterUserDTO())
                .build();
        when(userService.getUsers(any(UUID.class), anyLong())).thenReturn(mockResponse);

        mockMvc.perform(get("/user/all")
                        .param("id", userId.toString())
                        .header("idBussines", businessId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        verify(userService).getUsers(any(UUID.class), anyLong());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
