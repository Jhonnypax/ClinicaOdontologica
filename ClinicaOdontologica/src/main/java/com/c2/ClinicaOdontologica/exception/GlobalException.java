package com.c2.ClinicaOdontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ResorceNotFoundException.class})
    public ResponseEntity<String> tratamientoResorceNotFoundException(ResorceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

}
