package com.vid.vidbackend.global.error.model;

import lombok.Getter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Getter
public enum ErrorCode {
    // Common
    SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "C001", "Server Error"),
    INVALID_INPUT_VALUE(UNPROCESSABLE_ENTITY.value(), "C002", "Invalid Input Value"),
    METHOD_NOT_SUPPORT(METHOD_NOT_ALLOWED.value(), "G002", "Not Allowed HTTP Method"),
    ENTITY_NOT_FOUND(NOT_FOUND.value(), "G003", "Entity Not Found Exception"),

    // Address
    ADDRESS_NOT_FOUND(NOT_FOUND.value(), "AD001", "해당 주소는 존재하지 않는 주소입니다."),

    // Auction
    AUCTION_NOT_FOUND(NOT_FOUND.value(), "AU001", "해당 경매는 현재 진행중이지 않습니다."),
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
