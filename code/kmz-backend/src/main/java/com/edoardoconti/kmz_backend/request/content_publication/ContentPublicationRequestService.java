package com.edoardoconti.kmz_backend.request.content_publication;

import com.edoardoconti.kmz_backend.content.ContentService;
import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.feed.FeedService;
import com.edoardoconti.kmz_backend.request.RequestRepository;
import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class ContentPublicationRequestService extends RequestService {
    private final AuthService authService;
    private final ContentPublicationRequestRepository contentPublicationRequestRepository;
    private final ContentService contentService;
    private final FeedService feedService;




    public ContentPublicationResponseDto createContentPublicationRequest(Long contentId) {
        var content = this.contentService.getContent(contentId);
        if(content == null)
            return null;
        if(content.getStatus().equals(ContentStatus.PENDING) || content.getStatus().equals(ContentStatus.PUBLISHED))
            return null;
        var currentuser = this.authService.getCurrentUser();
        if (!content.getAuthorId().equals(currentuser.getId()))
            return null;
        var currentUser = this.authService.getCurrentUser();
        var request = new ContentPublicationRequest(currentUser.getId(), contentId);
        this.contentPublicationRequestRepository.save(request);
        this.contentService.setContentStatus(contentId, ContentStatus.PENDING);
        return ContentPublicationMapper.toDto(request, content);
    }

    public List<ContentPublicationResponseDto> getContentPublicationRequests() {
        var requests = this.contentPublicationRequestRepository.findAll();
        return requests.stream()
                .map(cr -> {
                    var content = this.contentService.getContent(cr.getContentId());
                    return ContentPublicationMapper.toDto(cr, content);
                })
                .toList();
    }

    public List<ContentPublicationResponseDto> getMyContentPublicationRequests() {
        var currentUser = this.authService.getCurrentUser();
        var requests = this.contentPublicationRequestRepository.findByRequesterId(currentUser.getId());
        return requests.stream()
                .map(cr -> {
                    var content = this.contentService.getContent(cr.getContentId());
                    return ContentPublicationMapper.toDto(cr, content);
                })
                .toList();
    }

    public ContentPublicationResponseDto getContentPublicationRequest(Long id) {
        var request = this.contentPublicationRequestRepository.findById(id).orElse(null);
        if(request == null)
            return null;
        var content = this.contentService.getContent(request.getContentId());
        return ContentPublicationMapper.toDto(request, content);
    }

    @Override
    public void approveRequest(Long requestId, String message) {
        var request =  this.contentPublicationRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        var approvedRequest = this.getApprovedRequest(request, message);
        this.contentPublicationRequestRepository.save(approvedRequest);
        var content = this.contentService.getContent(request.getContentId());
        if(content == null)
            throw new IllegalArgumentException("Content not found");
        this.feedService.publishContent(content);

    }

    @Override
    public void rejectRequest(Long requestId, String message) {
        var request =  this.contentPublicationRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        var rejectedRequest = this.getRejectedRequest(request, message);
        this.contentPublicationRequestRepository.save(rejectedRequest);
    }
}
