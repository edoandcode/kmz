package com.edoardoconti.kmz_backend.request.controllers;


import com.edoardoconti.kmz_backend.content.ContentService;
import com.edoardoconti.kmz_backend.request.Request;
import com.edoardoconti.kmz_backend.request.RequestRepository;
import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests/contents")
public class ContentPublicationRequestController {
    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final ContentService contentService;
    private final AuthService authService;

    @PostMapping("/publish/{contentId}")
    private ResponseEntity<Request> requestPublication(@PathVariable Long contentId) {
        var content = this.contentService.getContent(contentId);
        if(content == null)
            return ResponseEntity.status(404).build();
        var currentuser = this.authService.getCurrentUser();
        if (!content.getAuthorId().equals(currentuser.getId()))
            return ResponseEntity.status(403).build();
        var request = this.requestService.createContentPublicationRequest(contentId);
        this.requestRepository.save(request);
        return ResponseEntity.status(201).body(request);
    }


    @PostMapping("/approve/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.requestService.approve(requestId, message);
            return ResponseEntity.ok().body("Request approved successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to approve request: " + ex.getMessage());
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.requestService.reject(requestId, message);
            return ResponseEntity.ok().body("Request rejected successfully.");
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Failed to reject request: " + ex.getMessage());
        }
    }

}
