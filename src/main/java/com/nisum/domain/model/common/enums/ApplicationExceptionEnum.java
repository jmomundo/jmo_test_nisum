package com.nisum.domain.model.common.enums;

import com.nisum.domain.model.common.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionEnum implements IExceptionEnum {

    FORBIDDEN("004"),
    UNAUTHORIZED("005"),
    BAD_REQUEST("006");

    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.APPLICATION;
    private final String code;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}
