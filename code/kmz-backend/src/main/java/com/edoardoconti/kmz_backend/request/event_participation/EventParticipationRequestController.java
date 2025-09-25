package com.edoardoconti.kmz_backend.request.event_participation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests/event")
public class EventParticipationRequestController {
    private final EventParticipationRequestService eventParticipationRequestService;


    @GetMapping("/participation")
    public ResponseEntity<List<EventParticipationResponseDto>> getEventParticipationRequests() {
        return ResponseEntity.ok().body(this.eventParticipationRequestService.getEventParticipationRequests());
    }

    @GetMapping("/invite/{eventId}/{userId}")
    public ResponseEntity<EventParticipationResponseDto> inviteUserToEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        var request = this.eventParticipationRequestService.createContentPublicationRequest(eventId, userId);
        if(request == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(201).body(request);
    }

    @GetMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long requestId) {
        // TODO: implement
        return ResponseEntity.status(501).body("Not implemented yet");
    }

    @GetMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId) {
        // TODO: implement
        return ResponseEntity.status(501).body("Not implemented yet");
    }
}
