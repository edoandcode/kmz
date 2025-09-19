package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEventService implements EventService {

    private final EventRepository repository;
    private final EventContentMapper eventContentMapper;
    private final AuthService authService;

    @Override
    public EventContentDto uploadEvent(EventContentDto eventContentDto) {
        var event = this.eventContentMapper.toEntity(eventContentDto);
        var currentUser = this.authService.getCurrentUser();
        event.setAuthorId(currentUser.getId());
        this.repository.save(event);
        return this.eventContentMapper.toDto(event);
    }

    @Override
    public List<EventContentDto> getEvents() {
        return this.repository.findAll()
                .stream()
                .map(this.eventContentMapper::toDto)
                .toList();
    }

    @Override
    public EventContentDto getEvent(Long id) {
        var event =  this.repository.findById(id).orElse(null);
        return this.eventContentMapper.toDto(event);
    }

    @Override
    public List<EventContentDto> getMyEvents() {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .map(this.eventContentMapper::toDto)
                .toList();
    }

    @Override
    public EventContentDto getMyEvent(Long id) {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(this.eventContentMapper::toDto)
                .orElse(null);
    }
}
