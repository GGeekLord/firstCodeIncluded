package com.ecommerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class MyGlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(err -> {

            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });

        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFound(ResourceNotFoundException ex) {

        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> APIException(APIException ex) {

        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
