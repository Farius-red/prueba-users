package com.juliaosystem.infrastructure.entitis.pk;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRolPk  implements Serializable {

   @Column(name = "id_rol")
    private UUID rol;

    @Column(name = "id_usuario")
    private UUID user;

}
