package com.juliaosystem.utils.enums;


import org.springframework.http.HttpStatus;

public enum ResponseType {

    CREATED(1, MensajesRespuesta.CREADO.getMensaje(), true, HttpStatus.CREATED),
    UPDATED(2, MensajesRespuesta.ACTUALIZADO.getMensaje(), true, HttpStatus.OK),
    FALLO(3, MensajesRespuesta.FALLO.getMensaje(), false, HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_NO_ENCONTRADO(4 ,MensajesRespuesta.EMAIL_NO_ENCONTRADO.getMensaje(),false,HttpStatus.OK),

    USER_ISFOUND(5, MensajesRespuesta.USER_ISFOUND.getMensaje(), false, HttpStatus.OK),
    USER_lOGEADO(6, MensajesRespuesta.USER_lOGEADO.getMensaje(), false, HttpStatus.OK),
    EMAIL_VALIDATION_FAIL(7, EmailValidationPattern.EMAIL_VALIDATION_FAIL.getPattern(), false, HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUD(8,  EmailValidationPattern.EMAIL_NOT_FOUD.getPattern(), false, HttpStatus.OK),

    GET(10,MensajesRespuesta.GET.getMensaje(),true,HttpStatus.OK),
    NO_ENCONTRADO(11, MensajesRespuesta.NO_ENCONTRADO.getMensaje(), false, HttpStatus.OK),
    FALLO_CREATE_PHONE(12, MensajesRespuesta.FALLO_CREATE_PHONE.getMensaje(), false, HttpStatus.BAD_REQUEST),
    FALLO_CREATE_DATOS_USER(12,MensajesRespuesta.FALLO_CREATE_DATOS_USER.getMensaje() ,false ,HttpStatus.BAD_REQUEST );

    private final int code;

    private final String message;
    private final boolean isRta;
    private final HttpStatus httpStatus;

    ResponseType(int code, String message, boolean isRta, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.isRta = isRta;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public boolean isRta() {
        return isRta;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public  static  ResponseType fromMessage(String message){
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.message.equalsIgnoreCase(message)) {
                return responseType;
            }
        }
        return ResponseType.FALLO;
    }
    public static ResponseType fromCode(int code) {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.code == code) {
                return responseType;
            }
        }
        return null;
    }
}
