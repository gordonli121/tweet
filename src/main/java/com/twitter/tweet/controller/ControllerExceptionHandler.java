package com.twitter.tweet.controller;

import com.twitter.tweet.exception.ApiError;
import com.twitter.tweet.exception.PersistenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.twitter.tweet.exception.PersistenceException.ExceptionType;
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({PersistenceException.class})
    public ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        if (ex instanceof PersistenceException) {
            PersistenceException pe = (PersistenceException) ex;
            return this.handlePersistenceException(pe);
        }
        ApiError body = new ApiError(request.getContextPath(), "");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ApiError> handlePersistenceException(PersistenceException pe) {

        ApiError error = new ApiError(pe.getSource(), pe.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (pe.getType() == ExceptionType.NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        } else if (pe.getType() == ExceptionType.DUPLICATE) {
            status = HttpStatus.CONFLICT;
        } else if (pe.getType() == ExceptionType.NO_CONTENT) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(error, status);
    }

}
