package com.edoardoconti.kmz_backend.request.event_participation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests/events")
public class EventParticipationRequestController {
    private final EventParticipationRequestService eventParticipationRequestService;


    @GetMapping("/participation")
    public ResponseEntity<List<EventParticipationResponseDto>> getEventParticipationRequests() {
        return ResponseEntity.ok().body(this.eventParticipationRequestService.getEventParticipationRequests());
    }

    @GetMapping("/participation/me")
    public ResponseEntity<List<EventParticipationResponseDto>> getMyEventParticipationRequests() {
        return ResponseEntity.ok().body(this.eventParticipationRequestService.getMyEventParticipationRequests());
    }

    @PostMapping("/invite/{eventId}/{userId}")
    public ResponseEntity<EventParticipationResponseDto> inviteUserToEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        var request = this.eventParticipationRequestService.createEventParticipationRequest(eventId, userId);
        if(request == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(201).body(request);
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.eventParticipationRequestService.approveRequest(requestId, message);
            return ResponseEntity.ok().body("Request approved successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to approve request: " + ex.getMessage());
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.eventParticipationRequestService.rejectRequest(requestId, message);
            return ResponseEntity.ok().body("Request approved successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to approve request: " + ex.getMessage());
        }
    }
}
