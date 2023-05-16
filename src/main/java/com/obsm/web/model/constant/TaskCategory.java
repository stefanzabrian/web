package com.obsm.web.model.constant;

public enum TaskCategory {
    INSPECTION("Tower Inspections & Services"),
    MODIFICATION("Tower Modification & Reinforcement"),
    INSTALLATION("Equipment Installation & Upgrade"),
    SYSTEM("Distributed Antenna Systems Installation"),
    OPTIMIZATION("Optimization"),
    TESTING("Testing"),
    DEMOLITION("Decommission & Demolition"),
    MANAGEMENT("Construction Management"),
    CIVIL("Civil Construction General Site maintenance services"),
    HR("Human Resources"),
    INFORMATION("Detail Information");
    private final String displayName;

    TaskCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}