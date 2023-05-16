package com.obsm.web.model.constant;

public enum ProductModel {
    LAND("Land type"),
    AIR("Air type");
    private final String displayName;

    ProductModel(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }
}
