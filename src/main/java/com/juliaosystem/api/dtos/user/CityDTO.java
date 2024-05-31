package com.juliaosystem.api.dtos.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author daniel juliao
 * @version 1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Integer idCity;
    private String name;
    private Integer idCountry;
}
