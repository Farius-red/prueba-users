package com.juliaosystem.infrastructure.entitis;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_usuario")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id_usuario;

    @Column(name = "id_negocio")
    private  Long idBussines;

    @Column(name = "saldo")
    private Double balance;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    @OneToOne(mappedBy = "user")
    private DatesUser datesUser;

    @OneToMany(mappedBy = "user")
    private List<UserRol> userRols;




}
