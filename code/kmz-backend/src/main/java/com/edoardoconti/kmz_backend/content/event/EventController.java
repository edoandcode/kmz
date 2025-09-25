package com.edoardoconti.kmz_backend.content.event;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EventController {
    private final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<EventContentDto>> getEvents() {
        return ResponseEntity.ok(this.eventService.getEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventContentDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getEvent(id));
    }

    @GetMapping("/events/me")
    public ResponseEntity<List<EventContentDto>> getMyEvents() {
        return ResponseEntity.ok(this.eventService.getMyEvents());
    }

    @GetMapping("/events/me/{id}")
    public ResponseEntity<EventContentDto> getMyEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getMyEvent(id));
    }

    @PostMapping("/events")
    public ResponseEntity<EventContentDto> uploadEvent(@RequestBody @Valid EventContentDto eventContentDto) {
        var savedEvent = this.eventService.uploadEvent(eventContentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

}
