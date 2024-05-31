package com.juliaosystem.infrastructure.adapters.secondary;

import com.juliaosystem.infrastructure.entitis.Roles;
import com.juliaosystem.infrastructure.repository.RolRepository;
import com.juliaosystem.infrastructure.services.secondary.RolServiceInter;
import com.juliaosystem.utils.enums.RolEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolAdapter implements RolServiceInter {


    private final RolRepository rolRepository;

    @Override
    public Roles findByNombreRol(RolEnum nombreRol) {
        return rolRepository.findByNameRol(nombreRol);
    }
}
