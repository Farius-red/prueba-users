package com.juliaosystem.infrastructure.adapters.primary;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;

import com.juliaosystem.api.dtos.user.DatesUserDTO;
import com.juliaosystem.infrastructure.entitis.DatesUser;
import com.juliaosystem.infrastructure.entitis.User;
import com.juliaosystem.infrastructure.services.secondary.UserServiceInter;
import com.juliaosystem.utils.PlantillaResponse;
import com.juliaosystem.utils.UserResponses;
import com.juliaosystem.utils.enums.ResponseType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserImplTest {

    @Mock
    private UserServiceInter userServiceInter;

    @Mock
    private UserResponses<RegisterUserDTO> userResponses;

    @InjectMocks
    private UserImpl userImpl;


    @Test
    void testRegistro_ValidUser() {
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .message(ResponseType.CREATED.getMessage())
                        .rta(true)
                        .build();

        when(userServiceInter.addUser(any(RegisterUserDTO.class), any(PlantillaResponse.class)))
                .thenReturn(expectedResponse);

        when(userServiceInter.llenarEntidades(any(),any())).thenReturn(expectedResponse);

        PlantillaResponse<RegisterUserDTO> response = userImpl.registro(registerUserDTO, new PlantillaResponse<>());

        assertEquals(expectedResponse, response);
        verify(userServiceInter, times(1)).addUser(any(RegisterUserDTO.class), any(PlantillaResponse.class));
    }

    @Test
    void testAdd_ValidUser() {

        var user = User.builder().datesUser(DatesUser.builder()
                .idDatesUser(UUID.randomUUID())
                .firstName("daniel")
                .build()).build();
        RegisterUserDTO registerUserDTO = RegisterUserDTO.builder()
                .email("danie.juliao.tecni@gmail.com")
                .datesUser(DatesUserDTO.builder()
                        .idDatesUser(user.getDatesUser().getIdDatesUser())
                        .firstName(user.getDatesUser().getFirstName())
                        .idDatesUser(UUID.randomUUID())
                        .secondName("juliao")
                        .build())
                .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse = new PlantillaResponse<>();
        expectedResponse.setRta(true);
        expectedResponse.setData(registerUserDTO);

        expectedResponse.setMessage(ResponseType.CREATED.getMessage());
        when(userServiceInter.findByEmail(anyString())).thenReturn(new PlantillaResponse<>());
        when(userServiceInter.addUser(any(RegisterUserDTO.class), any(PlantillaResponse.class)))
                .thenReturn(PlantillaResponse.builder()
                        .data(user)
                        .message(ResponseType.CREATED.getMessage())
                        .build());
        when(userServiceInter.llenarEntidades(user, registerUserDTO)).thenReturn(expectedResponse);

        PlantillaResponse<RegisterUserDTO> response = userImpl.add(registerUserDTO);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetUsers_ByIdIsNull() {

        UUID id = null;
        long idBusiness = 123;
        PlantillaResponse<RegisterUserDTO> expectedResponse = new PlantillaResponse<>();
        when(userServiceInter.getUsers(idBusiness)).thenReturn(expectedResponse);


        PlantillaResponse<RegisterUserDTO> response = userImpl.getUsers(id, idBusiness);

        assertEquals(expectedResponse, response);
        verify(userServiceInter, times(1)).getUsers(idBusiness);
        verify(userServiceInter, never()).getUserById(any(UUID.class));
    }

    @Test
    void testGetUsers_ByIdIsNotNull() {
        UUID id = UUID.randomUUID();
        long idBusiness = 123;
        PlantillaResponse<RegisterUserDTO> expectedResponse = PlantillaResponse.<RegisterUserDTO>builder()
                .message(ResponseType.GET.getMessage())
                .build();

        when(userServiceInter.getUserById(id)).thenReturn(expectedResponse);

       var  response = userImpl.getUsers(id, idBusiness);

        assertEquals(expectedResponse, response);
        verify(userServiceInter, times(1)).getUserById(id);
        verify(userServiceInter, never()).getUsers(anyLong());
    }
}
