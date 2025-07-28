package com.edoardoconti.kmz_backend.role;

public enum UserRoleType {
    ADMINISTRATOR,
    CURATOR,
    FACILITATOR,
    GENERIC_USER,
    PROCESSOR,
    PRODUCER;


    public UserRole create() {
        return switch(this) {
            case ADMINISTRATOR -> new Administrator();
            case CURATOR -> new Curator();
            case FACILITATOR -> new Facilitator();
            case PROCESSOR -> new Processor();
            case PRODUCER -> new Producer();
            case GENERIC_USER -> new GenericUserRole();
        };
    }
}
