package com.juliaosystem.infrastructure.entitis;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "datos_usuarios")
public class DatesUser {
    @Column(name = "id_datos_usuario")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID idDatesUser;

    @Column(name = "primer_nombre")
    private String firstName;

    private String idUrl;

    @Column(name = "segundo_nombre")
    private String secondName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "datesUser")
    private List<Phone> phone;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "datesUser")
    private List<Address> addresses;

    @OneToOne
    @JoinColumn(name = "id_usuario", updatable = false, insertable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estates state;


    @Override
    public String toString() {
        return "DatesUser{" +
                "idDatesUser=" + idDatesUser +
                ", firstName='" + firstName + '\'' +
                ", idUrl='" + idUrl + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone=" + phone +
                ", addresses=" + addresses +
                ", user=" + (user != null ? user.getId_usuario() : null) +
                ", state=" + (state != null ? state.getIdEstate() : null) +
                '}';
    }


}
