package com.business.businessservice.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends CustomException{

    public static final int CODE = HttpStatus.BAD_REQUEST.value();

    public ValidationException(String message){
        super(CODE, message);
    }
}
