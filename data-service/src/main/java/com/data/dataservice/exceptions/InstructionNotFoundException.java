package com.data.dataservice.exceptions;

import org.springframework.http.HttpStatus;

public class InstructionNotFoundException extends CustomException {

    public static final int CODE = HttpStatus.NOT_FOUND.value();

    public InstructionNotFoundException(String message){
        super(CODE, message);
    }
}
