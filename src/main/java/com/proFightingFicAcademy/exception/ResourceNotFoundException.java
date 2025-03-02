package com.proFightingFicAcademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    //constructor
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
