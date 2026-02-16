package com.pramodvaddiraju.event_management_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandling.class);

    @ExceptionHandler(ResourceNotFoundExcpetion.class)
    ResponseEntity<Map<String,Object>> handleResourceNotFoundException(ResourceNotFoundExcpetion ex){
        Map<String,Object> response = new HashMap<>();
        response.put("Time stamp: ", LocalDateTime.now());
        response.put("Message: ", ex.getMessage());
        response.put("Status: ", HttpStatus.NOT_FOUND.value());
        log.error("Error fetching content: Not Found");
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String,Object>> handleValidationError(MethodArgumentNotValidException ex){
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(
                        error -> errors.put(error.getField(), error.getDefaultMessage())
                );
        return ResponseEntity.badRequest().body(errors);
    }
}
