package com.vid.vidbackend.exception.auction;

import com.vid.vidbackend.global.error.exception.EntityNotFoundException;
import com.vid.vidbackend.global.error.model.ErrorCode;

public class AuctionNotFoundException extends EntityNotFoundException {

    public AuctionNotFoundException() {
        super(ErrorCode.AUCTION_NOT_FOUND);
    }
}
