package com.vid.vidbackend.global.error.exception;

import com.vid.vidbackend.global.error.model.ErrorCode;

public class NotAcceptableException extends ApplicationException {

    public NotAcceptableException(final ErrorCode code) {
        super(code);
    }
}
