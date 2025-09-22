package com.edoardoconti.kmz_backend.request;

import com.edoardoconti.kmz_backend.request.requests.ContentPublicationRequest;
import com.edoardoconti.kmz_backend.request.requests.UserRegistrationRequest;
import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.user.User;

public interface RequestFactory {

    UserRegistrationRequest createUserRegistrationRequest(User user, UserRole role);
    ContentPublicationRequest createContentPublicationRequest(Long contentId);

}
