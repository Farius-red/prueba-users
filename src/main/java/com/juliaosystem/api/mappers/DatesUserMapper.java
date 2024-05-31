package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.api.dtos.user.DatesUserDTO;
import com.juliaosystem.infrastructure.entitis.DatesUser;
import com.juliaosystem.infrastructure.entitis.Estates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatesUserMapper implements PlantillaMapers<DatesUser, DatesUserDTO> {


   private final  PhoneMapper phoneMapper;


        private static char generarLetraAleatoria() {
            Random random = new Random();
            int randomInt = random.nextInt(26);
            char letraAleatoria = (char) (randomInt + 97);
            return letraAleatoria;
        }

        private static char generarDigitoAleatorio() {
            Random random = new Random();
            int randomInt = random.nextInt(10);
            char digitoAleatorio = (char) (randomInt + 48);
            return digitoAleatorio;
        }

        public static String generarCadenaAleatoria() {
            StringBuilder cadenaAleatoria = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                cadenaAleatoria.append(generarLetraAleatoria());
            }
            for (int i = 0; i < 3; i++) {
                cadenaAleatoria.append(generarDigitoAleatorio());
            }
            return cadenaAleatoria.toString();
        }


    @Override
    public List<DatesUserDTO> getListDTO(List<DatesUser> t) {
        List<DatesUserDTO> datesUserDTOS = new ArrayList<>();
        t.forEach(datesUser -> datesUserDTOS.add(getDTO(datesUser)));
        return datesUserDTOS ;
    }

    @Override
    public DatesUserDTO getDTO(DatesUser datesUser) {
        return DatesUserDTO.builder()
                .idDatesUser((datesUser.getIdDatesUser() != null)? datesUser.getIdDatesUser(): null)
                .phone(phoneMapper.getListDTO(datesUser.getPhone()))
                .idUrl(datesUser.getIdUrl())
                .firstName(datesUser.getFirstName())
                .secondName(datesUser.getSecondName())
                .build();
    }

    @Override
    public List<DatesUser> getListEntyti(List<DatesUserDTO> d) {
            List<DatesUser> datesUserList = new ArrayList<>();
            d.forEach(datesUserDTO -> datesUserList.add(getEntyti(datesUserDTO)) );
        return datesUserList;
    }
    public  DatesUser getEntityByRegisterUserDTO(RegisterUserDTO source){
          var datesUser =  DatesUserDTO.builder().
                    idDatesUser(source.getDatesUser().getIdDatesUser())
                    .firstName(source.getDatesUser().getFirstName() != null ?source.getDatesUser().getFirstName():"No Found")
                    .secondName(source.getDatesUser().getSecondName()!= null ? source.getDatesUser().getSecondName():"No Found")
                    .addresses(source.getDatesUser().getAddresses()!= null ? source.getDatesUser().getAddresses(): new ArrayList<>())
                    .estado(source.getDatesUser().getEstado())
                    .idUrl(source.getDatesUser().getIdUrl())
                    .phone(source.getDatesUser().getPhone()!= null ? source.getDatesUser().getPhone() : new ArrayList<>() )
                  .build();
            return getEntyti(datesUser);
    }
    @Override
    public DatesUser getEntyti(DatesUserDTO datesUserDTO) {
        return DatesUser.builder()
                .phone(phoneMapper.getListEntyti(datesUserDTO.getPhone()))
                .firstName(datesUserDTO.getFirstName())
                .secondName(datesUserDTO.getSecondName())
                .idUrl(datesUserDTO.getIdUrl())
                .idDatesUser(datesUserDTO.getIdDatesUser())
                .state(Estates.builder()
                        .idEstate(datesUserDTO.getEstado())
                        .build())
                .build();
    }
}
