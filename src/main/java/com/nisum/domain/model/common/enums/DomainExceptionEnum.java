package com.nisum.domain.model.common.enums;

import com.nisum.domain.model.common.interfaces.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionEnum implements IExceptionEnum {
    INVALID_EMAIL("001"),
    INVALID_PASSWORD("002");

    private static final ExceptionTypeEnum TYPE = ExceptionTypeEnum.DOMAIN;
    private final String code;

    @Override
    public ExceptionTypeEnum getType() {
        return TYPE;
    }
}
