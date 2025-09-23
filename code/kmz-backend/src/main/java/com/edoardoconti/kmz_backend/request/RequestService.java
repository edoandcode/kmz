package com.edoardoconti.kmz_backend.request;


import com.edoardoconti.kmz_backend.content.ContentService;
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
    private final ContentService contentService;

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
        System.out.println("Content ID: " + contentId);
        var currentUser = this.authService.getCurrentUser();
        var request = new ContentPublicationRequest(currentUser.getId(), contentId);
        System.out.println("Created Request: " + request);
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
    public UserRegistrationResponseDto getUserRegistrationRequest(Long id) {
        return this.getUserRegistrationRequests()
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ContentPublicationResponseDto> getContentPublicationRequests() {
        return this.requestRepository.findByType(RequestType.CONTENT_PUBLICATION)
                .stream()
                .map(r -> (ContentPublicationRequest) r)
                .map(req -> {
                    var content = this.contentService.getContent(req.getContentId());
                    return ContentPublicationResponseMapper.toDto(req, content);
                })
                .toList();
    }

    @Override
    public ContentPublicationResponseDto getContentPublicationRequest(Long id) {
        return this.getContentPublicationRequests()
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    ///  PRIVATE METHODS

    private void checkRequestProcessed(Request request) {
        if(request.getProcessedAt() != null)
            throw new IllegalStateException("Request already processed");
    }
}
