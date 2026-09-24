package com.stantzy.mediary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GenericEntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleGenericEntityNotFound(
        GenericEntityNotFoundException exception
    ) {
        ErrorDto errorDto = new ErrorDto(
            exception.getMessage(),
            Instant.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleBadRequest(
        IllegalArgumentException exception
    ) {
        ErrorDto errorDto = new ErrorDto(
            exception.getMessage(),
            Instant.now()
        );
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception exception) {
        ErrorDto errorDto = new ErrorDto(
            "Internal server error",
            Instant.now()
        );
        return ResponseEntity.internalServerError().body(errorDto);
    }
}
