package com.obsm.web.model.constant;

public enum OrderStatus {
    CREATED("Created"),
    PICKED_UP("Picked up"),
    DELIVERED("Delivered");
    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
