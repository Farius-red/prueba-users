package com.juliaosystem.infrastructure.adapters.secondary;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.api.dtos.user.DatesUserDTO;
import com.juliaosystem.api.mappers.UserMapper;
import com.juliaosystem.infrastructure.entitis.DatesUser;
import com.juliaosystem.infrastructure.entitis.User;
import com.juliaosystem.infrastructure.entitis.UserRol;
import com.juliaosystem.infrastructure.repository.UserRepository;
import com.juliaosystem.infrastructure.services.secondary.UserServiceInter;
import com.juliaosystem.utils.AbtractError;
import com.juliaosystem.utils.PlantillaResponse;
import com.juliaosystem.utils.UserResponses;
import com.juliaosystem.utils.enums.MensajesRespuesta;
import com.juliaosystem.utils.enums.ResponseType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

    @Mock
    private UserServiceInter userServiceInter;

    @Mock
    private UserResponses<RegisterUserDTO> userResponses;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AbtractError abtractError;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAdapter userAdapter;


    @Test
    void testFindByEmail_ValidEmail_UserFound() {
        String email = "test@example.com";

        User user = new User();
        user.setEmail(email);
        user.setUserRols(Collections.singletonList(new UserRol()));

        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.USER_ISFOUND.getMessage())
                        .rta(true)
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(expectedResponse.getMessage())
                .build());



        PlantillaResponse<RegisterUserDTO> response = userAdapter.findByEmail(email);


        assertEquals(ResponseType.USER_ISFOUND.getMessage(), response.getMessage());
        assertEquals(registerUserDTO, response.getData());
    }

    @Test
    void testFindByEmail_ValidEmail_UserNotFound() {

        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.NO_ENCONTRADO.getMessage())
                        .rta(true)
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(expectedResponse.getMessage())
                .build());

        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);


        PlantillaResponse<RegisterUserDTO> response = userAdapter.findByEmail(email);


        assertEquals(ResponseType.NO_ENCONTRADO.getMessage(), response.getMessage());
    }

    @Test
    void testFindByEmail_InvalidEmail() {
        long idBusiness = 123456;
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.EMAIL_VALIDATION_FAIL.getMessage())
                        .rta(true)
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(expectedResponse.getMessage())
                .build());

        String invalidEmail = "invalidemail";


        PlantillaResponse<RegisterUserDTO> response = userAdapter.findByEmail(invalidEmail);


        assertEquals(ResponseType.EMAIL_VALIDATION_FAIL.getMessage(), response.getMessage());
    }

    @Test
    void testLlenarEntidades_UserWithEmailAndEmptyResponse_Success() {

        long idBusiness = 123456;
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.CREATED.getMessage())
                        .rta(true)
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(expectedResponse.getMessage())
                .build());
        User userSave = new User();
        userSave.setEmail("test@example.com");
        PlantillaResponse<RegisterUserDTO> register = new PlantillaResponse<>();
        register.setRta(false);
        register.setMessage(MensajesRespuesta.NO_ENCONTRADO.getMensaje());

        PlantillaResponse<RegisterUserDTO> response = userAdapter.llenarEntidades(userSave, register.getData());


        assertEquals(ResponseType.CREATED.getMessage(), response.getMessage());
    }

    @Test
    void testLlenarEntidades_UserWithEmailAndFilledResponse_Success() {


        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.CREATED.getMessage())
                        .rta(true)
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(expectedResponse.getMessage())
                .build());

        User userSave = new User();
        userSave.setEmail("test@example.com");
        userSave.setUserRols(Collections.singletonList(new UserRol()));
        PlantillaResponse<RegisterUserDTO> register = new PlantillaResponse<>();
        register.setRta(true);
        register.setMessage(MensajesRespuesta.USER_ISFOUND.getMensaje());


        PlantillaResponse<RegisterUserDTO> response = userAdapter.llenarEntidades(userSave, register.getData());


        assertEquals(ResponseType.CREATED.getMessage(), response.getMessage());
    }

    @Test
    void testGetUsers_EmptyList_Success() {

        long idBusiness = 123456;
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.NO_ENCONTRADO.getMessage())
                        .rta(true)
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(expectedResponse.getMessage())
                .build());
        when(userRepository.findByIdBussines(idBusiness)).thenReturn(Collections.emptyList());


        PlantillaResponse<RegisterUserDTO> response = userAdapter.getUsers(idBusiness);

        assertEquals(ResponseType.NO_ENCONTRADO.getMessage(), response.getMessage());
    }

    @Test
    void testGetUsers_NonEmptyList_Success() {

        long idBusiness = 123456;
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.GET.getMessage())
                        .rta(true)
                        .build();


        var user = User.builder().datesUser(DatesUser.builder()
                        .idDatesUser(UUID.randomUUID())
                        .firstName(registerUserDTO.getDatesUser().getFirstName())
                .build())
                        .build();
        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(expectedResponse.getMessage())
                .build());

        when(userRepository.findByIdBussines(idBusiness)).thenReturn(Collections.singletonList(user));


        when(userMapper.getListDTO(Collections.singletonList(user))).thenReturn(Collections.singletonList(registerUserDTO));
        PlantillaResponse<RegisterUserDTO> response = userAdapter.getUsers(idBusiness);


        assertEquals(ResponseType.GET.getMessage(), response.getMessage());
    }

    @Test
    void testAddUser_UserNotFound_Success() {


        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.USER_ISFOUND.getMessage())
                        .rta(false)
                        .build();



        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(ResponseType.CREATED.getMessage())
                .build());


        PlantillaResponse<User> response = userAdapter.addUser(registerUserDTO, expectedResponse);


        assertEquals(ResponseType.CREATED.getMessage(), response.getMessage());
    }

    @Test
    void testAddUser_UserFound_Success() {

        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.USER_ISFOUND.getMessage())
                        .rta(false)
                        .build();



        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(ResponseType.USER_ISFOUND.getMessage())
                .build());


        PlantillaResponse<User> response = userAdapter.addUser(registerUserDTO, expectedResponse);


        assertEquals(ResponseType.USER_ISFOUND.getMessage(), response.getMessage());
    }

    @Test
    void testAddUser_ExceptionThrown_Failure() {

        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        PlantillaResponse<RegisterUserDTO> expectedResponse =
                PlantillaResponse.<RegisterUserDTO>builder()
                        .data(registerUserDTO)
                        .message(ResponseType.NO_ENCONTRADO.getMessage())
                        .rta(false)
                        .build();




        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(ResponseType.FALLO.getMessage())
                .build());

        PlantillaResponse<User> response = userAdapter.addUser(registerUserDTO, expectedResponse);


        assertEquals(ResponseType.FALLO.getMessage(), response.getMessage());
    }


    @Test
    void testGetUserById_UserNotFound() {

        UUID id = UUID.randomUUID();
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();



        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(ResponseType.NO_ENCONTRADO.getMessage())
                .build());

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());


        PlantillaResponse<RegisterUserDTO> response = userAdapter.getUserById(id);


        assertEquals(ResponseType.NO_ENCONTRADO.getMessage(), response.getMessage());

    }

    @Test
    void testGetUserById_UserFound() {

        UUID id = UUID.randomUUID();
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(ResponseType.GET.getMessage())
                .build());




        PlantillaResponse<RegisterUserDTO> response = userAdapter.getUserById(id);


        assertEquals(ResponseType.GET.getMessage(), response.getMessage());

    }

    @Test
    void testGetUserById_ExceptionThrown() {

        UUID id = UUID.randomUUID();
        RegisterUserDTO registerUserDTO =
                RegisterUserDTO.builder()
                        .datesUser(DatesUserDTO.builder()
                                .firstName("daniel")
                                .build())
                        .build();

        when(userResponses.buildResponse(anyInt(), any())).thenReturn(PlantillaResponse.<RegisterUserDTO>builder()
                .data(registerUserDTO)
                .message(ResponseType.FALLO.getMessage())
                .build());




        PlantillaResponse<RegisterUserDTO> response = userAdapter.getUserById(id);


        assertEquals(ResponseType.FALLO.getMessage(), response.getMessage());

    }
}