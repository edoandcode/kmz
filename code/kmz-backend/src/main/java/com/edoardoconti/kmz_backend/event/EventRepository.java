package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {
    private final List<Content> events = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Content content) {
        if(content.getType() != ContentType.EVENT)
            return;
        content.setId(nextId++);
        events.add(content);
    }

    public List<Content> getEvents() {
        return events;
    }


    public Content getEvent(Long id) {
        return events.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
}
