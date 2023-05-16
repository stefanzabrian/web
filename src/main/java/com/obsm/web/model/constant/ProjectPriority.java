package com.obsm.web.model.constant;

public enum ProjectPriority {
    CRITICAL("Critical"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    NO_PRIORITY("No priority");
    private  final String displayName;

    ProjectPriority(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

