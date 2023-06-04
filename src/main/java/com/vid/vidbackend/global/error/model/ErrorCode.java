package com.vid.vidbackend.global.error.model;

import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    // Common
    SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "C001", "Server Error"),
    INVALID_INPUT_VALUE(UNPROCESSABLE_ENTITY.value(), "C002", "Invalid Input Value"),
    METHOD_NOT_SUPPORT(METHOD_NOT_ALLOWED.value(), "G002", "Not Allowed HTTP Method"),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
