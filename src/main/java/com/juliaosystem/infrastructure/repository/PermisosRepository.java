package com.juliaosystem.infrastructure.repository;

import com.juliaosystem.infrastructure.entitis.Permiso;
import com.juliaosystem.utils.enums.PermisoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PermisosRepository extends JpaRepository<Permiso, UUID> {
    Permiso findByNombrePermiso(PermisoEnum nombrePermiso);
}
