package com.juliaosystem.infrastructure.entitis;


import com.juliaosystem.infrastructure.entitis.pk.UserRolPk;
import lombok.*;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "usuarios_rol")
public class UserRol {

    @EmbeddedId
    private UserRolPk userRolPk;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", updatable = false,insertable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "id_rol", updatable = false,insertable = false)
    private Roles rol;


}
