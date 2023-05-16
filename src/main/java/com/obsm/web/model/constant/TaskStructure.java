package com.obsm.web.model.constant;

public enum TaskStructure {
    UPLOAD("Compare & Upload file"),
    INSTRUCTIONS("Follow Instructions"),
    INFORMATION("Information");
    private final String displayName;


    TaskStructure(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
