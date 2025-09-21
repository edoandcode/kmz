package com.edoardoconti.kmz_backend.request;

import com.edoardoconti.kmz_backend.request.requests.ContentPublicationRequest;
import com.edoardoconti.kmz_backend.request.requests.UserRegistrationRequest;

import java.util.List;

public interface RequestQueryService {
    List<Request> getRequests();
    Request getRequest(Long id);
    List<UserRegistrationRequest> getUserRegistrationRequests();
    List<ContentPublicationRequest> getContentPublicationRequests();
}
