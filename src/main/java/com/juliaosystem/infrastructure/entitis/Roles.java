package com.juliaosystem.infrastructure.entitis;

import com.juliaosystem.utils.enums.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Roles {
    @Column(name = "id_rol")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID idRol;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre_rol")
    private RolEnum nameRol;

    private String  realm;

    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "creador")
    private String creator;

    @OneToMany(mappedBy = "rol")
    private List<UserRol> userRols ;
    @ManyToMany
    @JoinTable(
            name = "rol_permiso",
            joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id", referencedColumnName = "id_permiso")
    )
    private Set<Permiso> permisos;
}
