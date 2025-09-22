package com.edoardoconti.kmz_backend.request;


import com.edoardoconti.kmz_backend.request.requests.*;
import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.security.AuthService;

import com.edoardoconti.kmz_backend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RequestService implements RequestFactory, RequestQueryService {
    private final RequestRepository requestRepository;
    private final AuthService authService;

    public final void approve(Long requestId, String message) {
        var request = this.getRequest(requestId);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        this.checkRequestProcessed(request);
        request.setStatus(RequestStatus.APPROVED);
        request.setProcessedAt(new Date());
        if(message != null && !message.isBlank())
            request.setMessage(message);
        this.requestRepository.save(request);
    }


    public final void reject(Long requestId, String message) {
        var request = this.getRequest(requestId);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        this.checkRequestProcessed(request);
        request.setStatus(RequestStatus.REJECTED);
        request.setProcessedAt(new Date());
        if(message != null && !message.isBlank())
            request.setMessage(message);
        this.requestRepository.save(request);
    }

    @Override
    public UserRegistrationRequest createUserRegistrationRequest(User user, UserRole role) {
        var request = new UserRegistrationRequest(user, role);
        this.requestRepository.save(request);
        return request;
    }

    @Override
    public ContentPublicationRequest createContentPublicationRequest(Long contentId) {
        var currentUser = this.authService.getCurrentUser();
        var request = new ContentPublicationRequest(currentUser.getId(), contentId);
        this.requestRepository.save(request);
        return request;
    }

    @Override
    public List<Request> getRequests() {
        return this.requestRepository.findAll();
    }

    @Override
    public Request getRequest(Long id) {
        return this.requestRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRegistrationResponseDto> getUserRegistrationRequests() {
        return this.requestRepository.findByType(RequestType.USER_REGISTRATION)
                .stream()
                .map(r -> (UserRegistrationRequest) r)
                .map(UserRegistrationResponseMapper::toDto)
                .toList();
    }

    @Override
    public List<ContentPublicationResponseDto> getContentPublicationRequests() {
        return List.of();
    }


    ///  PRIVATE METHODS

    private void checkRequestProcessed(Request request) {
        if(request.getProcessedAt() != null)
            throw new IllegalStateException("Request already processed");
    }
}
