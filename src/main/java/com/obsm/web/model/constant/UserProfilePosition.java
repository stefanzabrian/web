package com.obsm.web.model.constant;

public enum UserProfilePosition {
    ELECTRICIAN("Electrician"),
    MECHANIC("Mechanic"),
    CONSTRUCTOR("Constructor"),
    DRIVER("Driver"),
    AMX("Viewer/Inspect"),
    ADMIN("Admin");

    private final String displayName;

    UserProfilePosition(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
