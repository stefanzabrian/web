package com.obsm.web.model.constant;

public enum ProjectCategory {
    INSPECTION("Tower Inspections & Services"),
    MODIFICATION("Tower Modification & Reinforcement"),
    INSTALLATION("Equipment Installation & Upgrade"),
    SYSTEM("Distributed Antenna Systems Installation"),
    OPTIMIZATION("Optimization"),
    TESTING("Testing"),
    DEMOLITION("Decommission & Demolition"),
    MANAGEMENT("Construction Management"),
    CIVIL("Civil Construction General Site maintenance services"),
    HR("Human Resources");

    private final String displayName;

    ProjectCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
