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
        System.out.println("REQUESTS ---> " + request);
        if(request == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(201).body(request);
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long requestId) {
        // TODO: implement
        return ResponseEntity.status(501).body("Not implemented yet");
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId) {
        // TODO: implement
        return ResponseEntity.status(501).body("Not implemented yet");
    }
}
