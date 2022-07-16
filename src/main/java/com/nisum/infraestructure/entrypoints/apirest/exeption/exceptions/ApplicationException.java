package com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions;

import com.nisum.domain.model.common.enums.ApplicationExceptionEnum;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final ApplicationExceptionEnum exception;

    public ApplicationException(Exception originalException, ApplicationExceptionEnum exception) {
        super(originalException);
        this.exception = exception;
    }
}
