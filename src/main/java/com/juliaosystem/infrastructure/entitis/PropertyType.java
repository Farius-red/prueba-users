package com.juliaosystem.infrastructure.entitis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tipo_inmueble")
public class PropertyType {
    @Id
    @UuidGenerator
    @Column(name = "id_tipo_inmueble", unique = true, nullable = false)
    private UUID idPropertyType;

    @Column(name = "nombre_tipo_inmueble")
    private String namePropertyType;

    @ManyToOne
    @JoinColumn(name = "id_detalle_direcciones",updatable = false,insertable = false)
    private Address address;
}
