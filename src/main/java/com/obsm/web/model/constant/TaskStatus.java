package com.obsm.web.model.constant;

public enum TaskStatus {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    PAUSED("Paused/Problem"),
    HANDLED("Handled");
    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
