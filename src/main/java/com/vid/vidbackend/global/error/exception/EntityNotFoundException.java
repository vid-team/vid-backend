package com.vid.vidbackend.global.error.exception;

import com.vid.vidbackend.global.error.model.ErrorCode;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
