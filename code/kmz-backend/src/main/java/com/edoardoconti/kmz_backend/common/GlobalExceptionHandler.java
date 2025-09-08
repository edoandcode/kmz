package com.edoardoconti.kmz_backend.common;

import com.edoardoconti.kmz_backend.admin.NoAdminFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoAdminFoundException.class)
    public ResponseEntity<String> handleNoAdminFoundError(NoAdminFoundException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        var errors = new HashMap<String, String>();

        Throwable cause = exception.getMostSpecificCause();

        if (cause instanceof InvalidFormatException invalidFormatException) {
            // Extract the path (field names) from the exception
            String field = invalidFormatException.getPath()
                    .stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .reduce((first, second) -> second) // take the last field in path
                    .orElse("requestBody");

            // Build a friendly message
            String message = "Invalid value for field '" + field + "'. Expected type: " +
                    invalidFormatException.getTargetType().getSimpleName();

            errors.put(field, message);
        } else {
            // fallback if we can't extract field info
            errors.put("requestBody", cause.getMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }
}
