package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.user.UserRolDTO;
import com.juliaosystem.infrastructure.entitis.UserRol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RolUserMapper implements PlantillaMapers<UserRol, UserRolDTO>{

   private final    RolMapper rolMapper;

    @Override
    public List<UserRolDTO> getListDTO(List<UserRol> t) {
        List<UserRolDTO>rolDTOS =new ArrayList<>();
        t.forEach(userRol -> rolDTOS.add(getDTO(userRol)));
        return rolDTOS;
    }

    @Override
    public UserRolDTO getDTO(UserRol userRol) {
        return UserRolDTO.builder()
                .rol(rolMapper.getDTO(userRol.getRol()))
                .build();
    }

    @Override
    public List<UserRol> getListEntyti(List<UserRolDTO> d) {
        List<UserRol> userRols =  new ArrayList<>();
        d.forEach(userRolDTO -> userRols.add(getEntyti(userRolDTO)));
        return userRols;
    }

    @Override
    public UserRol getEntyti(UserRolDTO userRolDTO) {
        return UserRol.builder()
                .rol(rolMapper.getEntyti(userRolDTO.getRol()))
                .build();
    }
}
