package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.user.PhoneDTO;
import com.juliaosystem.infrastructure.entitis.City;
import com.juliaosystem.infrastructure.entitis.Country;
import com.juliaosystem.infrastructure.entitis.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PhoneMapper implements PlantillaMapers<Phone, PhoneDTO> {

    @Override
    public List<PhoneDTO> getListDTO(List<Phone> t) {
        List<PhoneDTO> phoneDTOListList = new ArrayList<>();
        t.forEach(phone-> phoneDTOListList.add(getDTO(phone)));
        return phoneDTOListList;
    }

    @Override
    public PhoneDTO getDTO(Phone phone) {
        return PhoneDTO.builder()
                .cityCode(phone.getCity().getIdCity())
                .countryCode(phone.getCountry().getIdCountry())
                .nameCity(phone.getCity().getName())
                .nameCountry(phone.getCity().getName())
                .number(phone.getNumber())
                .build();
    }

    @Override
    public List<Phone> getListEntyti(List<PhoneDTO> d) {
         List<Phone> phoneList = new ArrayList<>();
        d.forEach(phoneDTO -> phoneList.add(getEntyti(phoneDTO)));
        return phoneList;
    }

    @Override
    public Phone getEntyti(PhoneDTO phoneDTO) {
       City city = City.builder()
                .name(phoneDTO.getNameCity())
                .idCity(phoneDTO.getCityCode())
                .build();

        Country country = Country.builder()
                .idCountry(phoneDTO.getCountryCode())
                .name(phoneDTO.getNameCountry())
                .cities(Collections.singletonList(city))
                .build();
        if(city.getCountry() == null) city.setCountry(country);
    return   Phone.builder()
                .country(country)
                .city(city)
                .number(phoneDTO.getNumber())
                .build();
    }
}
