package com.sc.userservice.exceptions;

import com.sc.userservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleRnfException(ResourceNotFoundException rnfExp) {
        String expMsg = rnfExp.getMessage();

        ApiResponse response = ApiResponse.builder().msg(expMsg)
            .reqSuccess(true).httpStatus(HttpStatus.NOT_FOUND)
            .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
