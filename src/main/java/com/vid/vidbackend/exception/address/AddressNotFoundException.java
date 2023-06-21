package com.vid.vidbackend.exception.address;

import com.vid.vidbackend.global.error.exception.EntityNotFoundException;
import com.vid.vidbackend.global.error.model.ErrorCode;

public class AddressNotFoundException extends EntityNotFoundException {

    public AddressNotFoundException() {
        super(ErrorCode.ADDRESS_NOT_FOUND);
    }
}
