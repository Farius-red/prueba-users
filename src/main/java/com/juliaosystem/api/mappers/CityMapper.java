package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.user.CityDTO;

import com.juliaosystem.infrastructure.entitis.City;
import com.juliaosystem.infrastructure.entitis.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CityMapper implements PlantillaMapers<City, CityDTO> {
    @Override
    public List<CityDTO> getListDTO(List<City> t) {
        List<CityDTO> cityDTOS =new ArrayList<>();
        t.forEach(city -> cityDTOS.add(getDTO(city)));
        return cityDTOS;
    }

    @Override
    public CityDTO getDTO(City city) {
        return CityDTO.builder()
                .idCity(city.getIdCity())
                .name(city.getName())
                .idCountry(city.getCountry().getIdCountry())
                .build();
    }

    @Override
    public List<City> getListEntyti(List<CityDTO> d) {
        List<City> cityList = new ArrayList<>();
         d.forEach(cityDTO -> cityList.add(getEntyti(cityDTO)));
        return cityList;
    }

    @Override
    public City getEntyti(CityDTO cityDTO) {
        return City.builder()
                .idCity(cityDTO.getIdCity())
                .name(cityDTO.getName())
                .country(Country.builder()
                        .idCountry(cityDTO.getIdCountry())
                        .build())
                .build();
    }
}
