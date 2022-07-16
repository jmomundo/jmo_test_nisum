package com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions;

import com.nisum.domain.model.common.enums.DomainExceptionEnum;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final DomainExceptionEnum exception;

    public DomainException(DomainExceptionEnum exception) {
        super(exception.getCode());
        this.exception = exception;
    }
}
