package com.juliaosystem.api.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author daniel juliao
 * @version 1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddresDTO {

    private String adress;
    private Set<PropertyTypeDTO> propertyTypes;
    private CityDTO cityDTO;
}
