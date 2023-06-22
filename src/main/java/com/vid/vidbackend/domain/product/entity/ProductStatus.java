package com.vid.vidbackend.domain.product.entity;

public enum ProductStatus {
    UNOPENED("미개봉");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
