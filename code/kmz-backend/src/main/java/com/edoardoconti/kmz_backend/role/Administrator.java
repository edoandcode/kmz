package com.edoardoconti.kmz_backend.role;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.user.UserAction;
import com.edoardoconti.kmz_backend.user.UserRegister;
import com.edoardoconti.kmz_backend.user.UserRequest;

public class Administrator implements UserRole {

    private UserRegister userRegister;

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

    public UserRequest getNextRequest() {
        return userRegister.getNextRequest();
    }

    public void approveRequest() {
        userRegister.processRequest(RequestStatus.APPROVED);
    }

    public void rejectRequest() {
        userRegister.processRequest(RequestStatus.REJECTED);
    }

}
