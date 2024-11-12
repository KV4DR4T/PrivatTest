package com.data.dataservice.exceptions;

import org.springframework.http.HttpStatus;

public class ResultNotFoundException extends CustomException {

    public static final int CODE = HttpStatus.NOT_FOUND.value();

    public ResultNotFoundException(String message){
        super(CODE, message);
    }
}
