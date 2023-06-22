package com.vid.vidbackend.domain.user.entity;

public enum BlockReason {
    FRAUD("사기를 당했어요.");

    final String message;

    BlockReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
