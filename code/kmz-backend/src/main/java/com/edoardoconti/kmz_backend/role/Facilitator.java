package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.user.User;
import com.edoardoconti.kmz_backend.user.UserAction;

public class Facilitator implements UserRole{

    @Override
    public boolean can(UserAction action) {
        return switch (action) {
            case VISUALIZE_CONTENT, CREATE_EVENT -> true;
            default -> false;
        };
    }

    @Override
    public UserAction[] can() {
        return new UserAction[]{UserAction.VISUALIZE_CONTENT, UserAction.CREATE_EVENT};
    }

    @Override
    public String getRoleName() {
        return "FACILITATOR";
    }
}
