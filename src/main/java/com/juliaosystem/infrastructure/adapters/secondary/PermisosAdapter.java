package com.juliaosystem.infrastructure.adapters.secondary;

import com.juliaosystem.infrastructure.entitis.Permiso;
import com.juliaosystem.infrastructure.repository.PermisosRepository;
import com.juliaosystem.infrastructure.services.secondary.PermisosInter;
import com.juliaosystem.utils.enums.PermisoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermisosAdapter implements PermisosInter {


    private  final PermisosRepository permisosRepository;

    @Override
    public Permiso findByNombrePermiso(PermisoEnum nombrePermiso) {
        return permisosRepository.findByNombrePermiso(nombrePermiso);
    }
}
