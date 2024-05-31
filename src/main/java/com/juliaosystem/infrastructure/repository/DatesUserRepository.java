package com.juliaosystem.infrastructure.repository;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.infrastructure.entitis.DatesUser;
import jakarta.transaction.Transactional;
import com.juliaosystem.api.dtos.user.PhoneDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DatesUserRepository extends JpaRepository<DatesUser, UUID> {
     @Transactional
    @Modifying
    @Query(value = "INSERT INTO datos_usuarios (id_datos_usuario, primer_nombre, id_url, segundo_nombre, id_usuario, id_estado) " +
            "VALUES (:#{#registerUserDTO.datesUser.idDatesUser},:#{#registerUserDTO.datesUser.firstName}, :#{#registerUserDTO.datesUser.idUrl}, " +
            ":#{#registerUserDTO.datesUser.secondName}, :#{#registerUserDTO.id}, :#{#registerUserDTO.datesUser.estado})" ,
            nativeQuery = true)
    void saveDatesUser(@Param("registerUserDTO") RegisterUserDTO registerUserDTO);


@Transactional
  @Modifying
  @Query(value = "INSERT INTO phones (number, ciudad_id, pais_id, id_datos_usuario)" +
          "VALUES(:#{#phoneDTO.number}, :#{#phoneDTO.cityCode}, :#{#phoneDTO.countryCode}," +
          ":idDatesUser)", nativeQuery = true)
  void savePhone(@Param("phoneDTO") PhoneDTO phoneDTO, @Param("idDatesUser") UUID idDatesUser);

}
