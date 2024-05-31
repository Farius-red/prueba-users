package com.juliaosystem.infrastructure.services.secondary;


import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.infrastructure.entitis.User;
import com.juliaosystem.utils.PlantillaResponse;

import java.util.UUID;

public interface UserServiceInter {

   PlantillaResponse<RegisterUserDTO> findByEmail(String email);



    PlantillaResponse<User> addUser(RegisterUserDTO registerUserDTO, PlantillaResponse<RegisterUserDTO> register);

    PlantillaResponse<RegisterUserDTO> llenarEntidades(User userSave , RegisterUserDTO registerUserDTO);

    PlantillaResponse<RegisterUserDTO> getUsers(long idBussines);

 PlantillaResponse<RegisterUserDTO> getUserById(UUID id);
}
