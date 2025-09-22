package com.edoardoconti.kmz_backend.request;

import com.edoardoconti.kmz_backend.request.requests.ContentPublicationResponseDto;
import com.edoardoconti.kmz_backend.request.requests.UserRegistrationResponseDto;

import java.util.List;

public interface RequestQueryService {
    List<Request> getRequests();
    Request getRequest(Long id);
    List<UserRegistrationResponseDto> getUserRegistrationRequests();
    List<ContentPublicationResponseDto> getContentPublicationRequests();
}
