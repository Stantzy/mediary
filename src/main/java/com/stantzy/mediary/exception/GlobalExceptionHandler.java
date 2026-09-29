package com.stantzy.mediary.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger =
        LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String DELIMITER = "; ";



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolation(
        ConstraintViolationException exception
    ) {
        logWarning(exception);

        Set<ConstraintViolation<?>> constraintViolations =
            exception.getConstraintViolations();

        String message = constraintViolations.stream()
            .map(cv ->
                extractFieldName(cv.getPropertyPath()) + ": " + cv.getMessage())
            .collect(Collectors.joining(DELIMITER));

        return ResponseEntity.badRequest()
            .body(new ErrorDto(message, Instant.now()));
    }

    private String extractFieldName(Path path) {
        String field = null;

        for(Path.Node node : path) {
            ElementKind nodeKind = node.getKind();
            if(
                nodeKind == ElementKind.PARAMETER ||
                nodeKind == ElementKind.PROPERTY
            ) {
                field = node.getName();
            }
        }

        return (field != null) ? field : path.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidation(
        MethodArgumentNotValidException exception
    ) {
        logWarning(exception);

        List<FieldError> fieldErrors =
            exception.getBindingResult().getFieldErrors();
        String message = formatFieldErrors(fieldErrors);

        ErrorDto errorDto = new ErrorDto(
            message,
            Instant.now()
        );

        return ResponseEntity.badRequest().body(errorDto);
    }

    private String formatFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors
            .stream()
            .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
            .collect(Collectors.joining(DELIMITER));
    }

    @ExceptionHandler(GenericEntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleGenericEntityNotFound(
        GenericEntityNotFoundException exception
    ) {
        logger.warn(
            "Handle {}: entity type={}, id={}, message={}",
            exception.getClass().getTypeName(),
            exception.getEntityType(),
            exception.getEntityId(),
            exception.getMessage()
        );
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
        logWarning(exception);

        ErrorDto errorDto = new ErrorDto(
            exception.getMessage(),
            Instant.now()
        );
        return ResponseEntity.badRequest().body(errorDto);
    }

    private void logWarning(Exception e) {
        logger.warn(
            "Handle {} exception: {}",
            e.getClass().getTypeName(),
            e.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception exception) {
        logger.error(
            "Handle {}: {}",
            exception.getClass().getSimpleName(),
            exception.getMessage(),
            exception
        );
        ErrorDto errorDto = new ErrorDto(
            "Internal server error",
            Instant.now()
        );
        return ResponseEntity.internalServerError().body(errorDto);
    }
}
