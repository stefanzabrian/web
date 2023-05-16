package com.obsm.web.model.constant;

public enum ProjectType {
    LAND("Land type"),
    AIR("Air type"),
    MANAGEMENT("HR Management");
    private final String displayName;

    ProjectType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}