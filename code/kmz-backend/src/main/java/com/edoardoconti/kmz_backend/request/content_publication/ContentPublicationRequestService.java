package com.edoardoconti.kmz_backend.request.content_publication;

import com.edoardoconti.kmz_backend.content.ContentService;
import com.edoardoconti.kmz_backend.request.RequestRepository;
import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContentPublicationRequestService extends RequestService {
    private final AuthService authService;
    private final ContentPublicationRequestRepository contentPublicationRequestRepository;
    private final ContentService contentService;



    public ContentPublicationResponseDto createContentPublicationRequest(Long contentId) {
        var currentUser = this.authService.getCurrentUser();
        var request = new ContentPublicationRequest(currentUser.getId(), contentId);
        this.contentPublicationRequestRepository.save(request);
        var content = this.contentService.getContent(contentId);
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
