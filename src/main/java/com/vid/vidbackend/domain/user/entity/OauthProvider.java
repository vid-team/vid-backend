package com.vid.vidbackend.domain.user.entity;

import java.util.Arrays;

public enum OauthProvider {
    KAKAO, GOOGLE;

    public static OauthProvider valueOfWithIgnoreCase(String value) {
        return Arrays.stream(values())
                .filter(oauthProvider -> oauthProvider.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow();
    }
}
