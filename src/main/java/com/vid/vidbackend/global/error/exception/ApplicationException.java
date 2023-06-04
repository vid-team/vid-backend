package com.vid.vidbackend.global.error.exception;

import com.vid.vidbackend.global.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApplicationException() {
        super(ErrorCode.SERVER_ERROR.getMessage());
        errorCode = ErrorCode.SERVER_ERROR;
    }

    public ApplicationException(String message) {
        super(message);
        errorCode = ErrorCode.SERVER_ERROR;
    }

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApplicationException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
