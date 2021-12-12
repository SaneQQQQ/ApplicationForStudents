package com.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomJsonResponse> entityNotFoundExceptionHandler(PersistenceException exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomJsonResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomJsonResponse> internalServerErrorHandler(Exception exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getMessage()),  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class CustomJsonResponse {
        private String message;

        public CustomJsonResponse(String message) {
            super();
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
