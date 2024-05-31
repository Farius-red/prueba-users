package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.user.CountryDTO;
import com.juliaosystem.infrastructure.entitis.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryMapper implements PlantillaMapers<Country, CountryDTO>  {


   private final CityMapper cityMapper;

    @Override
    public List<CountryDTO> getListDTO(List<Country> t) {
       List<CountryDTO> countryDTOList = new ArrayList<>();
       t.forEach(country -> countryDTOList.add(getDTO(country)));
        return  countryDTOList;
    }

    @Override
    public CountryDTO getDTO(Country country) {
        return CountryDTO.builder()
                .idCountry(country.getIdCountry())
                .url(country.getUrl())
                .name(country.getName())
                .coidgoIso(country.getCodigoIso())
                .cities(cityMapper.getListDTO(country.getCities()))
                .build();
    }

    @Override
    public List<Country> getListEntyti(List<CountryDTO> d) {
        List<Country> countryList =new ArrayList<>();
        d.forEach(countryDTO -> countryList.add(getEntyti(countryDTO)));
        return countryList;
    }

    @Override
    public Country getEntyti(CountryDTO countryDTO) {
        return Country.builder()
                .idCountry(countryDTO.getIdCountry())
                .cities(cityMapper.getListEntyti(countryDTO.getCities()))
                .name(countryDTO.getName())
                .codigoIso(countryDTO.getCoidgoIso())
                .url(countryDTO.getUrl())
                .build();
    }
}
