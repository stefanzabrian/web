package com.obsm.web.model.constant;

public enum UserRole {
    CLIENT("Client"),

    MODERATOR("Moderator"),
    AMX("AMX Role"),

    ADMIN("Admin");
    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}