package com.example.restpost.keycloak.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exception(Exception exception, HttpServletRequest request) {
        log.error("Erro exception");
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<StandardError> httpNotFound(HttpClientErrorException exception, HttpServletRequest request) {
        log.error("Erro HttpClientErrorException");
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                exception.getStatusCode().value(),
                exception.getStatusText(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exception.getStatusCode().value()).body(standardError);
    }

}
