package com.edoardoconti.kmz_backend.content.event;

import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.feed.FeedService;
import com.edoardoconti.kmz_backend.request.event_participation.EventParticipationRequest;
import com.edoardoconti.kmz_backend.request.event_participation.EventParticipationRequestRepository;
import com.edoardoconti.kmz_backend.request.event_participation.EventParticipationRequestService;
import com.edoardoconti.kmz_backend.security.AuthService;
import com.edoardoconti.kmz_backend.user.User;
import com.edoardoconti.kmz_backend.user.UserMapper;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEventService implements EventService {

    private final EventRepository repository;
    private final EventContentMapper eventContentMapper;
    private final AuthService authService;
    private final EventParticipationRequestRepository eventParticipationRequestRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final FeedService  feedService;

    @Override
    public EventContentDto uploadEvent(EventContentDto eventContentDto) {
        var event = this.eventContentMapper.toEntity(eventContentDto);
        event.setStatus(ContentStatus.DRAFT);
        var currentUser = this.authService.getCurrentUser();
        event.setAuthorId(currentUser.getId());
        this.repository.save(event);
        this.createParticipationRequestsForEachGuest(event, eventContentDto.getGuests());
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

    @Override
    public void addGuest(Long eventId, Long guestId) {
        var event = this.repository.findById(eventId).orElse(null);
        if(event == null)
            return;
        var guests = event.getGuests();
        if(guests.stream()
                .noneMatch(g -> g.getId().equals(guestId))) {
            var guest = this.userService.getUser(guestId);
            if(guest == null)
                return;
            guests.add(this.userMapper.toEntity(guest));
            event.setGuests(guests);
            this.repository.save(event);
        }
    }

    @Override
    public EventContentDto deleteEvent(Long id) {
        var event = this.repository.findById(id).orElse(null);
        var dto = this.eventContentMapper.toDto(event);
        if(event == null)
            return null;
        var currentUser = this.authService.getCurrentUser();
        if(!event.getAuthorId().equals(currentUser.getId()))
            return null;
        this.feedService.unpublishContent(event.getId());
        this.repository.delete(event);
        return dto;
    }


    //  private methods

    private void createParticipationRequestsForEachGuest(EventContent event, Iterable<User> guests) {
        for(User guest : guests) {
            this.eventParticipationRequestRepository.save(new EventParticipationRequest(event.getId(), event.getAuthorId(), guest.getId()));
        }
    }
}
