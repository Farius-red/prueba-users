package com.juliaosystem.infrastructure.services.primary;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.infrastructure.adapters.primary.UserImpl;
import com.juliaosystem.utils.PlantillaResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserImpl userImpl;


    public PlantillaResponse<RegisterUserDTO> addUser(RegisterUserDTO registerUserDTO) {
       return userImpl.add(registerUserDTO);
    }

    public PlantillaResponse<RegisterUserDTO> getUsers( UUID id, long idBussines) {
        return userImpl.getUsers(id,idBussines);
    }
}
