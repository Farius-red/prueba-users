package com.juliaosystem.api.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;


/**
 * @author daniel juliao
 * @version 1
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String idUser;

    @NotNull
    @NotBlank
    @NotEmpty
    @Min(3)
    @JsonProperty( "username")
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    @Min(8)
    @JsonProperty( "password")
    private String password;
    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty( "idUrl")
    private String idUrl;

    @JsonProperty( "balance")
    private Double balance;
    @Email
    @NotNull
    @NotBlank
    @NotEmpty

    @JsonProperty( "email")
    private String email;
    @JsonProperty( "createDate")
    private LocalDateTime createDate;

    @JsonProperty( "creator")
    private String creator;
    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty( "idBussines")
    private int idBussines;

    @JsonProperty( "updateDate")
    private LocalDateTime updateDate;

    @NotEmpty
    @NotNull
    @JsonProperty( "state")
    private EstatesUserDTO state;

    @NotEmpty
    @NotNull
    @JsonProperty( "datesUser")
    private DatesUserDTO datesUser;

    @JsonProperty( "userRols")
    private UserRolDTO userRols;


    @JsonProperty( "autority")
    private  String autority;


}
