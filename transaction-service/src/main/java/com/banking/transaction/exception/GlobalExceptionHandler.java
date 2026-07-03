package com.banking.transaction.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handle(TransactionNotFoundException ex){

        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TransactionFailedException.class)
    public ResponseEntity<String> handle(TransactionFailedException ex){

      
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_GATEWAY);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex){

        return new ResponseEntity<>(
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){

        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
