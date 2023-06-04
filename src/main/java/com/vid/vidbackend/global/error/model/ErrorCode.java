package com.vid.vidbackend.global.error.model;

import lombok.Getter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Getter
public enum ErrorCode {
    // Common
    SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "C001", "Server Error"),
    INVALID_INPUT_VALUE(UNPROCESSABLE_ENTITY.value(), "C002", "Invalid Input Value"),
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
