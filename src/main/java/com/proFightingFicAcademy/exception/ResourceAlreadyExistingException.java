package com.proFightingFicAcademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistingException extends RuntimeException{

    //constructor
    public ResourceAlreadyExistingException(String message) {
        super(message);
    }

}
