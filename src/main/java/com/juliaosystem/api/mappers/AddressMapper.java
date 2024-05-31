package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.user.AddresDTO;
import com.juliaosystem.infrastructure.entitis.Address;
import com.juliaosystem.infrastructure.entitis.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel ="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddressMapper {

    @Mapping(target = "city", source = "cityDTO")
    @Mapping(target = "city.country", source = "cityDTO.idCountry", qualifiedByName = "mapCountry")
    Address mapToEntity(AddresDTO source);




    @Named("mapCountry")
    default Country mapCountry(Integer idCountry) {
        if (idCountry == null) {
            return null;
        }

        return Country.builder().idCountry(idCountry).build();
    }

    List<Address> mapToEntityList(List<AddresDTO> sourceList);

}
