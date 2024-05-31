package com.juliaosystem.utils.mappers;

import java.util.List;

/**
 * @description Recibe en el parametro T la entidad y en D Dto
 * @Autor daniel juliao
 * @param <T> entidad
 * @param <D> dto
 * @version 1
 * @return retorna una entidad t
 */
public interface PlantillaMapperGetEntity <T,D>{
    List<T> getListEntyti(List<D> d);
    T getEntyti(D d);
}
