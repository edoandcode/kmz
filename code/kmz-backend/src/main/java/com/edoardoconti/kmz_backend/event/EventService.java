package com.edoardoconti.kmz_backend.event;

import java.util.List;

public interface EventService {
    EventContentDTO uploadEvent(EventContentDTO eventContent);
    List<EventContentDTO> getEvents();
    EventContentDTO getEvent(Long id);
}
