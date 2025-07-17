package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.user.User;

public abstract class CreatorRole implements UserRole {
    @Override
    public boolean canVisualize(Content content, User user) {
        return true;
    }

    public abstract boolean canCreate(Content content, User user);
}
