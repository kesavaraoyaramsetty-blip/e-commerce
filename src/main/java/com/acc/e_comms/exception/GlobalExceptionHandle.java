//Implementing Global Exception Handler with RestControllerAdvice annotation & ExceptionHandler annotation
package com.acc.e_comms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleInvalidMethodArgumentType(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid value : " + ex.getValue() + " for :" + ex.getName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleInvalidMethodArguments(MethodArgumentNotValidException ex) {
//        String message = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(error->error.getDefaultMessage())
//                .collect(Collectors.joining(","));

        // if we want the return type as list
        List<String> message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
