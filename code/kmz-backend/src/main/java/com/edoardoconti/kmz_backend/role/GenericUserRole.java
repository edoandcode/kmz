package com.edoardoconti.kmz_backend.role;


import com.edoardoconti.kmz_backend.user.UserAction;

public class GenericUserRole implements UserRole {

    @Override
    public boolean can(UserAction action) {
        return switch (action) {
            case VISUALIZE_CONTENT -> true;
            default -> false;
        };
    }

    @Override
    public UserAction[] can() {
        return new UserAction[]{UserAction.VISUALIZE_CONTENT};
    }

    @Override
    public String getRoleName() {
        return "GENERIC_USER";
    }
}
