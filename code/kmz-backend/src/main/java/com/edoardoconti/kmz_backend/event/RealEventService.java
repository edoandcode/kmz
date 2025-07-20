package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEventService implements EventService {

    private EventRepository repository;

    public RealEventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void uploadEvent(Event event) {
        this.repository.save(event);
    }

    @Override
    public List<Event> getEvents() {
        return this.repository.getEvents();
    }

    @Override
    public Event getEvent(Long id) {
        return this.repository.getEvent(id);
    }
}
