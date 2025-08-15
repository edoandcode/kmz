package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEventService implements EventService {

    private final EventRepository repository;

    @Override
    public Event uploadEvent(Event event) { return this.repository.save(event); }

    @Override
    public List<Event> getEvents() {
        return this.repository.findAll();
    }

    @Override
    public Event getEvent(Long id) {
        return this.repository.findById(id).orElse(null);
    }
}
