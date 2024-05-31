package com.juliaosystem.utils;


import com.juliaosystem.utils.enums.ResponseType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  Recibe en el parametro E la entidad  es escargada de Costruir las respuestas Revisar Enum ResponseType
 *  importante int tipoRespuesta se puede ver o definir en ResponseType
 * @author  daniel juliao
 * @param <E> entidad
 *  {@link ResponseType}
 * @version 1
 */
@Component
public class UserResponses<E> {
    public PlantillaResponse<E> buildResponse(int tipoRespuesta, E e) {
        return buildResponse(tipoRespuesta, e, null);
    }

    /**
     * Construye una respuesta estándar con una única entidad.
     *
     * @param tipoRespuesta Código que representa el tipo de respuesta deseada.
     * @param e             La entidad que se incluirá en la respuesta.
     * @return Una instancia de PlantillaResponse que representa la respuesta construida.
     * {@link ResponseType}
     *  {@link PlantillaResponse}
     * @throws IllegalArgumentException Si el tipo de respuesta proporcionado no es válido.
     */
    public PlantillaResponse<E> buildResponse(int tipoRespuesta, E e , List<E> listE) {
        ResponseType responseType = ResponseType.fromCode(tipoRespuesta);
        if (responseType != null) {
            return PlantillaResponse.<E>builder()
                    .message(responseType.getMessage())
                    .data(e)
                    .dataList(listE)
                    .rta(responseType.isRta())
                    .httpStatus(responseType.getHttpStatus())
                    .build();
        }

        throw new IllegalArgumentException("Tipo de respuesta no válido: " + tipoRespuesta);
    }
}
