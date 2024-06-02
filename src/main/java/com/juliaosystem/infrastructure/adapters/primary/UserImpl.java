package com.juliaosystem.infrastructure.adapters.primary;


import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.infrastructure.services.secondary.UserServiceInter;
import com.juliaosystem.utils.PlantillaResponse;
import com.juliaosystem.utils.UserResponses;

import com.juliaosystem.utils.enums.ResponseType;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserImpl {


    private final UserServiceInter userServiceInter;



    private final UserResponses<RegisterUserDTO> userResponses;



    public PlantillaResponse<RegisterUserDTO> registro(RegisterUserDTO registerUserDTO , PlantillaResponse<RegisterUserDTO> register ){
        var userSave = userServiceInter.addUser(registerUserDTO, register);
        if(userSave.getMessage().equalsIgnoreCase((ResponseType.CREATED.getMessage())))
           return userServiceInter.llenarEntidades(userSave.getData(), registerUserDTO);
        else return userResponses.buildResponse(ResponseType.fromMessage(userSave.getMessage()).getCode(), RegisterUserDTO.builder().build());
    }



    public  PlantillaResponse<RegisterUserDTO> add(RegisterUserDTO registerUserDTO){
         return  registro(registerUserDTO,userServiceInter.findByEmail(registerUserDTO.getEmail()));
    }


    public PlantillaResponse<RegisterUserDTO> getUsers(UUID id, Long idBussines) {

     if(id  == null) {
         if(idBussines == null)
             return userServiceInter.all();
         else
            return userServiceInter.getUsers(idBussines);
        }else
            return userServiceInter.getUserById(id);
    }
}
