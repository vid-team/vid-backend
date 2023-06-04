package com.vid.vidbackend.global.error.exception;

import com.vid.vidbackend.global.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidValueException extends ApplicationException {

    protected static final String EMAIL = "email";

    private final String field;
    private String value;

    public InvalidValueException(final String message, final ErrorCode errorCode, final String field) {
        super(message, errorCode);
        this.field = field;
    }

    public InvalidValueException(final String message, final ErrorCode errorCode, final String field, final String value) {
        this(message, errorCode, field);
        this.value = value;
    }
}
