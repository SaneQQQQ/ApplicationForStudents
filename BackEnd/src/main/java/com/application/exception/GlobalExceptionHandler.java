package com.application.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomJsonResponse> internalServerErrorHandler(Exception exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoResultException.class, NonUniqueResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomJsonResponse> persistenceExceptionHandler(PersistenceException exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomJsonResponse> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).findFirst().get()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CustomJsonResponse> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(new CustomJsonResponse(exception.getCause().getCause().getMessage()), HttpStatus.CONFLICT);
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
