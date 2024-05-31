package com.juliaosystem.api.mappers;


import com.juliaosystem.utils.mappers.PlantillaMapperGetDTO;
import com.juliaosystem.utils.mappers.PlantillaMapperGetEntity;

/**
 * @description Recibe en el parametro T la entidad y en D Dto
 * @Autor daniel juliao
 * @param <T> entidad
 * @param <D> dto
 * @version 1
 */
public interface PlantillaMapers<T,D> extends PlantillaMapperGetEntity<T, D>, PlantillaMapperGetDTO<T,D> {

}
