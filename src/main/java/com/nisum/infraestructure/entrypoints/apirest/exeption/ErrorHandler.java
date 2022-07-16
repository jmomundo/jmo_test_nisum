package com.nisum.infraestructure.entrypoints.apirest.exeption;

import com.nisum.domain.model.common.enums.InfrastructureExceptionEnum;
import com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions.DomainException;
import com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions.InfrastructureException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> domainErrorHandler(DomainException exception) {
        var domainEnum = exception.getException();
        switch (domainEnum) {
            case INVALID_EMAIL:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(APPLICATION_JSON)
                        .body(Map.of("mensaje", "Email invalido"));

            case INVALID_PASSWORD:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(APPLICATION_JSON)
                        .body(Map.of("mensaje", "Password invalido"));
            default:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(APPLICATION_JSON)
                        .body(Map.of("mensaje", "Error inesperado"));

        }
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<Object> infrastructureErrorHandler(InfrastructureException exception) {
        var domainEnum = exception.getException();
        if (domainEnum == InfrastructureExceptionEnum.USER_NOT_FOUND) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(APPLICATION_JSON)
                    .body(Map.of("mensaje", "Usuario no encontrado"));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(Map.of("mensaje", "Error inesperado"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception exception) {

        if (exception instanceof DataIntegrityViolationException) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(APPLICATION_JSON)
                    .body(Map.of("mensaje", "El correo ya esta registrado"));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(Map.of("mensaje", "Error inesperado"));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> internalErrorHandler() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(Map.of("mensaje", "Error inesperado"));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> runtimeErrorHandler() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(Map.of("mensaje", "Error inesperado"));
    }



}
