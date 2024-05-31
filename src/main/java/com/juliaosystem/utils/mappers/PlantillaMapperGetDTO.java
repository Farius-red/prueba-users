package com.juliaosystem.utils.mappers;

import java.util.List;

/**
 * @description Recibe en el parametro T la entidad y en D Dto
 * @Autor daniel juliao
 * @param <T> entidad
 * @param <D> dto
 * @version 1
 * @return retorna un dto
 */
public interface PlantillaMapperGetDTO <T,D>{

    List<D> getListDTO(List<T> t);
    D getDTO(T t);
}
