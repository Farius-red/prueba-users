package com.juliaosystem.api.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author daniel juliao
 * @version 1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstatesUserDTO {
    private  Integer idStateUser;

    @NotEmpty
    @NotNull
    @NotBlank
    @Min(3)
    @JsonProperty( "nameState")
    private String nameState;
    @JsonProperty( "description")
    private String description;
}
