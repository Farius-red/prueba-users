package com.juliaosystem.infrastructure.entitis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "estados")
public class Estates {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_estado")
    private UUID idEstate;

    @NotEmpty
    @Column(name = "nombre_estado")
    private String nameState;

    @Column(name = "descripcion")
    private String description;


}
