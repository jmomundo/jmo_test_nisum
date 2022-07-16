package com.nisum.domain.model.common.enums;

import com.nisum.domain.model.common.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfrastructureExceptionEnum implements IExceptionEnum {

    USER_NOT_FOUND("003");

    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.INFRASTRUCTURE;
    private final String code;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}

