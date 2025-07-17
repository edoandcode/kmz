package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.user.User;

public class Producer extends CreatorRole{
    @Override
    public boolean canCreate(Content content, User user) {
        // TODO
        return content.getType() == ContentType.PRODUCT && user.can();
    }
}
