package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.user.UserAction;

public interface UserRole {
     boolean can(UserAction action);
     String getRoleName();
     UserAction[] can();
}
