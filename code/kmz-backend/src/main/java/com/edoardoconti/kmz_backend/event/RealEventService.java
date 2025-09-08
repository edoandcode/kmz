package com.edoardoconti.kmz_backend.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEventService implements EventService {

    private final EventRepository repository;
    private final EventContentMapper eventContentMapper;

    @Override
    public EventContentDto uploadEvent(EventContentDto eventContentDto) {
        var event = this.eventContentMapper.toEntity(eventContentDto);
        this.repository.save(event);
        eventContentDto.setId(event.getId());
        return eventContentDto;
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
}
