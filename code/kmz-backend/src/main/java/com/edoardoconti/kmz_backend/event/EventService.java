package com.edoardoconti.kmz_backend.event;

import java.util.List;

public interface EventService {
    void uploadEvent(Event event);
    List<Event> getEvents();
    Event getEvent(Long id);
}
