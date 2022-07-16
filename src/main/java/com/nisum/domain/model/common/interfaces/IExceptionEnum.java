package com.nisum.domain.model.common.interfaces;


import com.nisum.domain.model.common.enums.ExceptionTypeEnum;

public interface IExceptionEnum {

    String name();

    String getCode();

    ExceptionTypeEnum getType();

}
