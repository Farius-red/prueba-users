package com.juliaosystem.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PlantillaResponse<E>{
    private boolean rta;
    private String message;
    private HttpStatus httpStatus;
    private E data;
    private List<E> dataList;
}
