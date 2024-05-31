package com.juliaosystem.infrastructure.repository;

import com.juliaosystem.infrastructure.entitis.UserRol;
import com.juliaosystem.infrastructure.entitis.pk.UserRolPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<UserRol, UserRolPk> {
}
