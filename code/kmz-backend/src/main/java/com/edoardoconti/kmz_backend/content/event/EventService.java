package com.edoardoconti.kmz_backend.content.event;

import java.util.List;

public interface EventService {
    EventContentDto uploadEvent(EventContentDto eventContent);
    List<EventContentDto> getEvents();
    EventContentDto getEvent(Long id);
    List<EventContentDto> getMyEvents();
    EventContentDto getMyEvent(Long id);
    EventContent addGuest(Long eventId, Long guestId);
}
