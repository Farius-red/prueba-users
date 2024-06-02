package com.juliaosystem.infrastructure.adapters.secondary;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.api.mappers.AddressMapper;
import com.juliaosystem.api.mappers.DatesUserMapper;
import com.juliaosystem.api.mappers.UserMapper;
import com.juliaosystem.infrastructure.entitis.Permiso;
import com.juliaosystem.infrastructure.entitis.Roles;
import com.juliaosystem.infrastructure.entitis.User;
import com.juliaosystem.infrastructure.entitis.UserRol;
import com.juliaosystem.infrastructure.entitis.pk.UserRolPk;
import com.juliaosystem.infrastructure.repository.AddressRepository;
import com.juliaosystem.infrastructure.repository.DatesUserRepository;
import com.juliaosystem.infrastructure.repository.UserRepository;
import com.juliaosystem.infrastructure.repository.UserRolRepository;
import com.juliaosystem.infrastructure.services.secondary.UserServiceInter;
import com.juliaosystem.utils.AbtractError;
import com.juliaosystem.utils.PlantillaResponse;
import com.juliaosystem.utils.UserResponses;

import com.juliaosystem.utils.enums.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAdapter   implements UserServiceInter {

    private static final Logger logger = LoggerFactory.getLogger(UserAdapter.class);
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().registerModule(new JavaTimeModule());
    private final UserResponses<RegisterUserDTO> userResponses;
    private final  UserResponses<User> userUserResponsesUser;


    private final AbtractError abtractError;

    private final UserMapper userMapper;
    private final PermisosAdapter permisosAdapter;
    private final RolAdapter rolAdapter;
    private  final UserRolRepository userRolRepository;
    private final UserRepository userRepository;
    private final DatesUserRepository datesUserRepository;
    private  final AddressRepository addressRepository;
    private  final AddressMapper addressMapper;
    private  final DatesUserMapper datesUserMapper;


    @Override
    public PlantillaResponse<RegisterUserDTO> findByEmail(String email) {
          User user;
        if (!validarEmail(email))
            return userResponses.buildResponse(ResponseType.EMAIL_VALIDATION_FAIL.getCode(), RegisterUserDTO.builder().build());
         else
            user = userRepository.findByEmail(email);

        if (user != null) {
            RegisterUserDTO userDTO = userMapper.getDTO(user);
            if(user.getDatesUser() != null) {
                userDTO.getDatesUser().setNombreRol(user.getUserRols().getFirst().getRol().getNameRol().name());
            }
            return userResponses.buildResponse(ResponseType.USER_ISFOUND.getCode(), userDTO);
        } else
            return userResponses.buildResponse(ResponseType.NO_ENCONTRADO.getCode(), RegisterUserDTO.builder().build());
    }


    private PlantillaResponse<RegisterUserDTO> buildRegisterUserResponse(User user) {
        RegisterUserDTO userDTO = userMapper.getDTO(user);
        return userResponses.buildResponse(ResponseType.CREATED.getCode(), userDTO );
    }




    private boolean validarEmail(String email){
      return email.matches(EmailValidationPattern.VALID.getPattern());
    }



    private  Set<Permiso> asignarPermisoNuevoUsuario(){
    Set<Permiso> permisosCliente = new HashSet<>();
    permisosCliente.add(permisosAdapter.findByNombrePermiso(PermisoEnum.NUEVO_USUARIO));
    return  permisosCliente;
    }

    private User asignarRolYPermisosNuevoUsuario(User user){
    Roles rol = rolAdapter.findByNombreRol(RolEnum.USUARIO);
    rol.setPermisos(asignarPermisoNuevoUsuario());
    user.setUserRols(Collections.singletonList(UserRol.builder().rol(rol).build()));
    return user;
    }

    @Override
    public PlantillaResponse<RegisterUserDTO> llenarEntidades(User userSave , RegisterUserDTO registerUserDTO){
    try {
        if (!userSave.getEmail().isEmpty()) {
            registerUserDTO.setId(userSave.getId_usuario());
            registerUserDTO.getDatesUser().setIdDatesUser(UUID.randomUUID());
            registerUserDTO.getDatesUser().setIdUrl(DatesUserMapper.generarCadenaAleatoria());
            if(registerUserDTO.getDatesUser().getEstado() == null){
                registerUserDTO.setEstado("3fa85f64-5717-4562-b3fc-2c963f66afa6");
            }
            datesUserRepository.saveDatesUser(registerUserDTO);
            abtractError.logInfo("llenarEntidades().SaveDatesUser():" + MensajesRespuesta.CREADO +" los datos de usuario  " +  OBJECT_MAPPER.writeValueAsString(registerUserDTO.getDatesUser()));
            fillPhone(registerUserDTO);
           fillAddress(registerUserDTO);
            userRolRepository.saveAll(guardarUserRol(userSave));

            abtractError.logInfo("llenarEntidades() :" + MensajesRespuesta.CREADO +" los datos de usuario rol   " +  OBJECT_MAPPER.writeValueAsString(userSave.getUserRols()));
            return buildRegisterUserResponse(userSave);
        }
    }catch (Exception e){
        return validarErrorResponsellenarEntidades(e,registerUserDTO);
    }
     return  userResponses.buildResponse(ResponseType.FALLO.getCode(),RegisterUserDTO.builder()
             .build());
   }

    @Override
    public PlantillaResponse<RegisterUserDTO> getUsers(long idBussines) {
         try {
            var listUsers = userMapper.getListDTO(userRepository.findByIdBussines(idBussines));
            if(listUsers.isEmpty()){
                abtractError.logInfo("UserAdapter.getUsers():" + MensajesRespuesta.NO_ENCONTRADO +"  de usuario para el negocio con id  " +  OBJECT_MAPPER.writeValueAsString(idBussines));
                 return userResponses.buildResponse(ResponseType.NO_ENCONTRADO.getCode(), RegisterUserDTO.builder().build());
            }else {
             abtractError.logInfo("UserAdapter.getUsers():" + MensajesRespuesta.GET +" los datos de usuario para el negocio " +     OBJECT_MAPPER.writeValueAsString(listUsers));
             return userResponses.buildResponse(ResponseType.GET.getCode(), RegisterUserDTO.builder().build(),listUsers);
            }
         }catch (Exception e){
             abtractError.logError(e);
            return   userResponses.buildResponse(ResponseType.FALLO.getCode(), RegisterUserDTO.builder().build());
         }
    }

    @Override
    public PlantillaResponse<RegisterUserDTO> getUserById(UUID id) {
        try {
           var listUsers = userRepository.findById(id);
            if(listUsers.isEmpty()){
                abtractError.logInfo("getUserById.getUsers():" + MensajesRespuesta.NO_ENCONTRADO +"  de usuario para el   id  " +  OBJECT_MAPPER.writeValueAsString(id));
                return userResponses.buildResponse(ResponseType.NO_ENCONTRADO.getCode(), RegisterUserDTO.builder().build());
            }else {
                var listRegisterDTO = userMapper.getListDTO(listUsers.stream().toList());
                abtractError.logInfo("getUserById.getUsers():" + MensajesRespuesta.GET +" de usuario  " +     OBJECT_MAPPER.writeValueAsString(listUsers));
                return userResponses.buildResponse(ResponseType.GET.getCode(), RegisterUserDTO.builder().build(),listRegisterDTO);
            }
        }catch (Exception e){
            abtractError.logError(e);
            return   userResponses.buildResponse(ResponseType.FALLO.getCode(), RegisterUserDTO.builder().build());
        }
    }

    @Override
    public PlantillaResponse<RegisterUserDTO> all() {
        try {
            var listUsers = userRepository.findAll();
            if(listUsers.isEmpty()){
                abtractError.logInfo("userAdapter.all():" + MensajesRespuesta.NO_ENCONTRADO +"de usuarios" +  OBJECT_MAPPER.writeValueAsString(listUsers));
                return userResponses.buildResponse(ResponseType.NO_ENCONTRADO.getCode(), RegisterUserDTO.builder().build());
            }else {
                var listRegisterDTO = userMapper.getListDTO(listUsers.stream().toList());
                abtractError.logInfo("userAdapter.all():" + MensajesRespuesta.GET +" de usuario" );
                return userResponses.buildResponse(ResponseType.GET.getCode(), RegisterUserDTO.builder().build(),listRegisterDTO);
            }
        }catch (Exception e){
            abtractError.logError(e);
            return   userResponses.buildResponse(ResponseType.FALLO.getCode(), RegisterUserDTO.builder().build());
        }
    }

    private void fillPhone(RegisterUserDTO registerUserDTO){
        try {
            if (registerUserDTO.getDatesUser().getPhone() != null && !registerUserDTO.getDatesUser().getPhone().isEmpty() ) {
                registerUserDTO.getDatesUser().getPhone().forEach(phoneDTO -> datesUserRepository.savePhone(phoneDTO, registerUserDTO.getDatesUser().getIdDatesUser()));
                abtractError.logInfo("llenarEntidades().phone :" + MensajesRespuesta.CREADO + " los telefonos del usuario  " + OBJECT_MAPPER.writeValueAsString(registerUserDTO.getDatesUser().getPhone()));
            }
        }catch (Exception e){
            abtractError.logError(e);
        }
   }

    private  void fillAddress(RegisterUserDTO registerUserDTO){
       try {
           if (registerUserDTO.getDatesUser().getAddresses() != null && !registerUserDTO.getDatesUser().getPhone().isEmpty()) {
               var listAddress =addressMapper.mapToEntityList(registerUserDTO.getDatesUser().getAddresses());
               listAddress.forEach(item -> {
                   if(  item.getDatesUser() == null ){
                       item.setDatesUser(datesUserMapper.getEntyti(registerUserDTO.getDatesUser()));
                   }
                   addressRepository.save(item);

               });
               abtractError.logInfo("llenarEntidades().address :" + MensajesRespuesta.CREADO + " las direcciones  del usuario  " + OBJECT_MAPPER.writeValueAsString(registerUserDTO.getDatesUser().getPhone()));

           }
       }catch (Exception e){
           abtractError.logError(e);
       }
   }


   private   PlantillaResponse<RegisterUserDTO> validarErrorResponsellenarEntidades(Exception e , RegisterUserDTO registerUserDTO){
       abtractError.logError(e);
        if (e.getMessage().contains("fkqyhoaebvd8qqsobu7jddhm7f3"))
           return  userResponses.buildResponse(ResponseType.FALLO_CREATE_PHONE.getCode(),registerUserDTO);
        if(e.getMessage().contains("fkc6t6ir1ytmvrpjf1me1c1tr6j"))
            return  userResponses.buildResponse(ResponseType.FALLO_CREATE_DATOS_USER.getCode(),registerUserDTO);

       return  userResponses.buildResponse(ResponseType.FALLO.getCode(),registerUserDTO);
    }

   @Override
   @Transactional(propagation = Propagation.REQUIRED)
    public PlantillaResponse<User> addUser(RegisterUserDTO registerUserDTO, PlantillaResponse<RegisterUserDTO> register){
        PlantillaResponse<User> response = new PlantillaResponse<>();
        try {
            if (!register.isRta() && register.getMessage().equalsIgnoreCase(MensajesRespuesta.NO_ENCONTRADO.getMensaje())) {
                User user = asignarRolYPermisosNuevoUsuario(userMapper.getEntyti(registerUserDTO));
                user.setBalance(0.0);
                User userSave = userRepository.save(user);
                response = userUserResponsesUser.buildResponse(ResponseType.CREATED.getCode(), userSave);
            }
            if (register.getMessage().equalsIgnoreCase(MensajesRespuesta.USER_ISFOUND.getMensaje()))
                return userUserResponsesUser.buildResponse(ResponseType.USER_ISFOUND.getCode(), User.builder().build());
        }catch (Exception e){
            logger.error("Algo salio mal en metodo UserAdapter.verificarReponseAdd()" , e);
            response=  userUserResponsesUser.buildResponse(ResponseType.FALLO.getCode(), User.builder().build());
        }

        return response;
    }


    private List<UserRol>   guardarUserRol(User userSave){
        List<UserRol> userRols = userSave.getUserRols();
        userRols.forEach(userRol -> {
            userRol.setUserRolPk(UserRolPk.builder()
                    .user(userSave.getId_usuario())
                    .rol(userRol.getRol().getIdRol())
                    .build());
            userRol.setRol(Roles.builder()
                            .idRol(userRol.getUserRolPk().getRol())
                    .build());
        });
        return userRols;
    }









}
