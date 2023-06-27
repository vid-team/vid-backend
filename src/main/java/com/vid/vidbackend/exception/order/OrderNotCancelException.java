package com.vid.vidbackend.exception.order;

import com.vid.vidbackend.global.error.exception.NotAcceptableException;
import com.vid.vidbackend.global.error.model.ErrorCode;

public class OrderNotCancelException extends NotAcceptableException {

    public OrderNotCancelException() {
        super(ErrorCode.ORDER_NOT_CANCEL);
    }
}
