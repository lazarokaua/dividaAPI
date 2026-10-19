package com.lazarokaua.dividaAPI.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lazarokaua.dividaAPI.domain.divida.exception.DividaNotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DividaNotFoundException.class)
    public ResponseEntity<Void> handleDividaNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<java.util.Map<String, String>> handleValidationExceptions(
        org.springframework.web.bind.MethodArgumentNotValidException ex) {
        java.util.Map<String, String> errors = new java.util.HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
        errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
