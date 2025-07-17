package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.user.User;

public class Curator implements UserRole{
    @Override
    public boolean canVisualize(Content content, User user) {
        return true;
    }

    public boolean canApprove(Content content, User user) {
        // TODO
        return user.can();
    }
}
