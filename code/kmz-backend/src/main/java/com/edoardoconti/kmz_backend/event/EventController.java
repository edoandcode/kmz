package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.ok(this.service.getEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getEvent(id));
    }

    @PostMapping
    public ResponseEntity<Void> uploadProduct(@RequestBody Event event) {
        this.service.uploadEvent(event);
        return ResponseEntity.status(201).build();
    }


}
