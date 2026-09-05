//Implementing Global Exception Handler with RestControllerAdvice annotation & ExceptionHandler annotation
package com.acc.e_comms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        //return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMethodArgumentType(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid value : " + ex.getValue() + " for :" + ex.getName();
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                LocalDateTime.now()
        );
        //return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMethodArguments(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.joining(","));

        // if we want the return type as list
//        List<String> message = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(error->error.getDefaultMessage())
//                .toList();
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                LocalDateTime.now()
        );
        //return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
