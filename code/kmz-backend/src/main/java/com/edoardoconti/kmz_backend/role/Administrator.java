package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.user.UserAction;

public class Administrator implements UserRole {

    @Override
    public boolean can(UserAction action) {
        return true;
    }

    @Override
    public UserAction[] can() {
        return UserAction.values();
    }

    @Override
    public String getRoleName() {
        return "ADMINISTRATOR";
    }


}
