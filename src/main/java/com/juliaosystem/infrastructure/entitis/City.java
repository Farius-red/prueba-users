package com.juliaosystem.infrastructure.entitis;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ciudades")
public class City {
    @Column(name = "id_ciudad")
    @Id
    @NotNull
    private Integer idCity;

    @Column(name = "nombre")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_Pais", insertable = false,updatable = false)
    private Country country;

    @Override
    public String toString() {
        return "City{" +
                "id_city=" + idCity +
                ", name='" + name + '\'' +
                '}';
    }

}
