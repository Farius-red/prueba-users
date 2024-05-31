package com.juliaosystem.infrastructure.entitis;

import com.juliaosystem.utils.enums.PermisoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permisos")
public class Permiso {

    @Column(name = "id_permiso")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID idPermiso;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre_permiso")
    private PermisoEnum nombrePermiso;
}
