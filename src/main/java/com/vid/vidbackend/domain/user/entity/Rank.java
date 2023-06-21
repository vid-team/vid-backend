package com.vid.vidbackend.domain.user.entity;

import java.util.Arrays;

public enum Rank {
    UNRANK(0),
    BRONZE(100),
    SILVER(200),
    GOLD(300),
    PLATINUM(400),
    DIAMOND(500),
    MASTER(600);

    final int xp;

    Rank(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    public static Rank getRankForXp(int xp) {
        return Arrays.stream(Rank.values())
                .filter(rank -> xp >= rank.getXp())
                .reduce((first, second) -> second)
                .orElse(Rank.UNRANK);
    }
}
