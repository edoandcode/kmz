package com.edoardoconti.kmz_backend.request.content_publication;


import com.edoardoconti.kmz_backend.content.ContentService;
import com.edoardoconti.kmz_backend.feed.FeedService;
import com.edoardoconti.kmz_backend.request.Request;
import com.edoardoconti.kmz_backend.request.RequestRepository;
import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.request.RequestStatus;
import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests/contents")
public class ContentPublicationRequestController {
    private final ContentPublicationRequestService contentPublicationRequestService;
    private final ContentService contentService;
    private final AuthService authService;
    private final FeedService feedService;


    @GetMapping("/publication")
    public List<ContentPublicationResponseDto> getContentPublicationRequests(
            @RequestParam(value = "status", required = false) RequestStatus status,
            @RequestParam(value = "authorId", required = false) Long authorId
    ) {
        var contentRequests = this.contentPublicationRequestService.getContentPublicationRequests();
        if (status != null)
            contentRequests = contentRequests.stream().filter(r -> r.getStatus() == status).toList();
        if (authorId != null)
            contentRequests = contentRequests.stream().filter(r -> {
                var content = r.getContent();
                return content != null && content.getAuthorId().equals(authorId);
            }).toList();
        return contentRequests;
    }


    @PostMapping("/publish/{contentId}")
    private ResponseEntity<ContentPublicationResponseDto> requestPublication(@PathVariable Long contentId) {
        var content = this.contentService.getContent(contentId);
        if(content == null)
            return ResponseEntity.status(404).build();
        var currentuser = this.authService.getCurrentUser();
        if (!content.getAuthorId().equals(currentuser.getId()))
            return ResponseEntity.status(403).build();
        var request = this.contentPublicationRequestService.createContentPublicationRequest(contentId);
        return ResponseEntity.status(201).body(request);
    }


    @PostMapping("/approve/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            var request = this.contentPublicationRequestService.getContentPublicationRequest(requestId);
            if (request == null)
                return ResponseEntity.badRequest().body("Request not found.");
            var content = request.getContent();
            if(content == null)
                return ResponseEntity.badRequest().body("Content not found.");
            this.feedService.publishContent(content);
            this.contentPublicationRequestService.approveRequest(requestId, message);
            return ResponseEntity.ok().body("Request approved successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to approve request: " + ex.getMessage());
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.contentPublicationRequestService.rejectRequest(requestId, message);
            return ResponseEntity.ok().body("Request rejected successfully.");
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Failed to reject request: " + ex.getMessage());
        }
    }

}
