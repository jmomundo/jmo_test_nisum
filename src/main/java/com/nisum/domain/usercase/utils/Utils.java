package com.nisum.domain.usercase.utils;

import com.nisum.domain.model.common.enums.DomainExceptionEnum;
import com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions.DomainException;

import java.util.regex.Pattern;

public class Utils {

    public static void validPatternEmail(String email) {
        Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (!patternEmail.matcher(email).matches()) {
            throw new DomainException(DomainExceptionEnum.INVALID_EMAIL);
        }
    }
    public static void validPatternPassword(String pass) {
        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        if (!patternPassword.matcher(pass).matches()) {
            throw new DomainException(DomainExceptionEnum.INVALID_PASSWORD);
        }

    }
}
