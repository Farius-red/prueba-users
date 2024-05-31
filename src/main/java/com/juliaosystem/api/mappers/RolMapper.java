package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.user.RolDTO;
import com.juliaosystem.infrastructure.entitis.Roles;
import com.juliaosystem.utils.enums.RolEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RolMapper implements PlantillaMapers<Roles, RolDTO>{


    @Override
    public List<RolDTO> getListDTO(List<Roles> t) {
        List<RolDTO> rolDTOS = new ArrayList<>();
        t.forEach(rol -> rolDTOS.add(getDTO(rol)));
        return rolDTOS;
    }

    @Override
    public RolDTO getDTO(Roles rol) {
        return RolDTO.builder()
                .idRol(rol.getIdRol())
                .nameRol(rol.getNameRol().name())
                .build();
    }

    @Override
    public List<Roles> getListEntyti(List<RolDTO> d) {
        List<Roles> rols = new ArrayList<>();
        d.forEach(rolDTO -> rols.add(getEntyti(rolDTO)));
        return rols;
    }

    @Override
    public Roles getEntyti(RolDTO rolDTO) {
        return Roles.builder()
                .idRol(rolDTO.getIdRol())
                .nameRol(RolEnum.valueOf(rolDTO.getNameRol()))
                .build();
    }
}
