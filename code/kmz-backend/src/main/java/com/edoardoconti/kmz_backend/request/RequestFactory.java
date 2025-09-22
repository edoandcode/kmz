package com.edoardoconti.kmz_backend.request;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.request.requests.ContentPublicationRequest;
import com.edoardoconti.kmz_backend.request.requests.UserRegistrationRequest;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import com.edoardoconti.kmz_backend.user.User;

public interface RequestFactory {

    UserRegistrationRequest createUserRegistrationRequest(User user, UserRoleType role);
    ContentPublicationRequest createContentPublicationRequest(Long contentId);

}
