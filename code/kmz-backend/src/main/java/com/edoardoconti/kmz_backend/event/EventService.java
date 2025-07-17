package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EventService {
    void uploadEvent(Content content);
    List<Content> getEvents();
    Content getEvent(Long id);
}
