package com.juliaosystem.infrastructure.entitis;

import lombok.*;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Getter
@Setter
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "\\d{7}", message = "El número de teléfono debe tener 7 dígitos")
    private String number;

    @NotBlank(message = "El código de ciudad es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id")
    private City city;

    @NotBlank(message = "El código de país es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "id_datos_usuario",updatable = false, insertable = false)
    private DatesUser datesUser;

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", city=" + (city != null ? city.getIdCity() : null) +
                ", country=" + (country != null ? country.getIdCountry() : null) +
                ", datesUser=" + (datesUser != null ? datesUser.getIdDatesUser() : null) +
                '}';
    }

}
