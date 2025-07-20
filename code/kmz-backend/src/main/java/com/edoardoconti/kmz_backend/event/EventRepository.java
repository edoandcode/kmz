package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.ContentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {
    private final List<Event> events = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Event event) {
        if(event.getType() != ContentType.EVENT)
            return;
        event.setId(nextId++);
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }


    public Event getEvent(Long id) {
        return events.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
}
