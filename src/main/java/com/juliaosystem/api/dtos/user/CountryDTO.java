package com.juliaosystem.api.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author daniel juliao
 * @version 1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private int idCountry;
    private String name;
    private String url;
    private String coidgoIso;
    private List<CityDTO> cities;
}
