package com.juliaosystem.infrastructure.entitis;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "direcciones")
@Getter
@Setter
public class Address {

    @Column(name = "id_detalle_direcciones")
    @Id
    @UuidGenerator
    private UUID idAddress;

    @Column(name = "direccion")
    private String adress;

    @OneToMany(fetch =  FetchType.EAGER,mappedBy = "address")
    private Set<PropertyType> propertyTypes = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "id_city" ,insertable = false, updatable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "id_datos_usuario",updatable = false, insertable = false)
    private DatesUser datesUser;

}
