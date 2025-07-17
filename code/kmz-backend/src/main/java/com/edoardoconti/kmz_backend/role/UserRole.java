package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.user.User;

public interface UserRole {
    public boolean canVisualize(Content content, User user);
}
