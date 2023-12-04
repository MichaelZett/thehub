package com.buhl.hub.common.values;

public enum HubRole {
    ADMIN, USER;

    public String nameWithRome() {
        return "ROLE_%s".formatted(name());
    }
}
