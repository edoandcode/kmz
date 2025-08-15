package com.edoardoconti.kmz_backend.event;

import java.util.List;

public interface EventService {
    Event uploadEvent(Event event);
    List<Event> getEvents();
    Event getEvent(Long id);
}
