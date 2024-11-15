package com.data.dataservice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleException(CustomException e, HttpServletRequest request) {
        log.error("Got exception: ", e);
        ApiError apiError = buildError(request.getRequestURI(), e.getMessage(), e.getCode());
        return ResponseEntity.status(apiError.statusCode()).body(apiError);
    }

    private ApiError buildError(String uri, String message, int code) {
        ApiError apiError = new ApiError(uri, message, code);
        log.info("Created api error: {}", apiError);
        return apiError;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e, HttpServletRequest request) {

        log.error("Got exception: ", e);
        ApiError apiError = buildError(request.getRequestURI(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(apiError.statusCode()).body(apiError);
    }
}
