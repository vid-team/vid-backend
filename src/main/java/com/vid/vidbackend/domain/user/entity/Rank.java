package com.vid.vidbackend.domain.user.entity;

public enum Rank {
    BRONZE(100),
    SILVER(200),
    GOLD(300),
    PLATINUM(400),
    DIAMOND(500),
    MASTER(600);
    int xp;

    Rank(int xp) {
        this.xp = xp;
    }
}
