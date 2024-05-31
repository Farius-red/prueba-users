package com.juliaosystem.infrastructure.repository;

import com.juliaosystem.infrastructure.entitis.Roles;
import com.juliaosystem.utils.enums.RolEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolRepository extends JpaRepository<Roles, UUID> {

    Roles findByNameRol(RolEnum nombreRol);
}
