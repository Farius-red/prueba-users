package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.api.dtos.user.DatesUserDTO;
import com.juliaosystem.infrastructure.entitis.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UserMapper implements PlantillaMapers<User, RegisterUserDTO> {


    private final PhoneMapper phoneMapper;


    @Override
    public List<RegisterUserDTO> getListDTO(List<User> t) {
        List<RegisterUserDTO> registerUserDTOList = new ArrayList<>();
        t.forEach(user -> registerUserDTOList.add(getDTO(user)));
        return registerUserDTOList;
    }


    @Override
    public RegisterUserDTO getDTO(User user) {
        return RegisterUserDTO.builder()
                .idBussines(user.getIdBussines())
                .id(user.getId_usuario())
                .email(user.getEmail())
                .estado((user.getDatesUser()!= null && user.getDatesUser().getState() != null)?user.getDatesUser().getState().getNameState(): "INACTIVE")
                .datesUser(DatesUserDTO.builder()
                        .secondName((user.getDatesUser() != null )?user.getDatesUser().getSecondName(): "")
                        .firstName((user.getDatesUser()!= null)?user.getDatesUser().getFirstName():"")
                        .phone(((user.getDatesUser()!= null) && !user.getDatesUser().getPhone().isEmpty())?phoneMapper.getListDTO(user.getDatesUser().getPhone()): List.of())
                        .idUrl((user.getDatesUser()!= null)?user.getDatesUser().getIdUrl():"")
                        .idDatesUser((user.getDatesUser()!= null)?user.getDatesUser().getIdDatesUser(): null)
                        .build())
                .build();
    }

    @Override
    public List<User> getListEntyti(List<RegisterUserDTO> d) {
        List<User>userList = new ArrayList<>();
        d.forEach(registerUserDTO -> userList.add(getEntyti(registerUserDTO)));
        return userList;
    }

    @Override
    public User getEntyti(RegisterUserDTO registerUserDTO) {
        return User.builder()
                .idBussines(registerUserDTO.getIdBussines())
                .email(registerUserDTO.getEmail())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
               // .datesUser(registerUserDTO.getDatesUser())
                .build();
    }
}
