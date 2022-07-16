package com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions;

import com.nisum.domain.model.common.enums.InfrastructureExceptionEnum;
import lombok.Getter;

@Getter
public class InfrastructureException extends RuntimeException {
    private final InfrastructureExceptionEnum exception;

    public InfrastructureException(Exception originalException, InfrastructureExceptionEnum exception) {
        super(originalException);
        this.exception = exception;
    }

    public InfrastructureException(String message, InfrastructureExceptionEnum exception) {
        super(message);
        this.exception = exception;
    }

    public InfrastructureException(Throwable cause, InfrastructureExceptionEnum exception) {
        super(cause);
        this.exception = exception;
    }
}
