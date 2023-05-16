package com.obsm.web.model.constant;

public enum ProjectStatus {
    WAITING_FOR_CUSTOMER("Waiting for Customer"),
    BLOCKED("Blocked"),
    IMPLEMENTATION("Implementation"),
    OPEN("Open"),
    DELIVERED("Delivered");
    private final String displayName;

    ProjectStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
