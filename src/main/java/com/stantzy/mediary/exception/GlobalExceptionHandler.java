package com.stantzy.mediary.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFound(
        EntityNotFoundException exception
    ) {
        ErrorDto errorDto = new ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            exception.getMessage(),
            Instant.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception exception) {
        ErrorDto errorDto = new ErrorDto(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            Instant.now()
        );
        return ResponseEntity.internalServerError().body(errorDto);
    }
}
