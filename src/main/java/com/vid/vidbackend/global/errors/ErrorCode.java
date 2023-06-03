package com.vid.vidbackend.global.errors;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    ;

    private final String code;
    private final String status;
    private final String message;

    ErrorCode(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
