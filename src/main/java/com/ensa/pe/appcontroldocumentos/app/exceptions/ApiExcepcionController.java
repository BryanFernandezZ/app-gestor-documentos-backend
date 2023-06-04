package com.ensa.pe.appcontroldocumentos.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExcepcionController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundException(NotFoundException e) {
        return new ResponseEntity(ApiExceptionDTO.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity internalServerException(InternalServerException e) {
        return new ResponseEntity(
                ApiExceptionDTO.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now().toString())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
