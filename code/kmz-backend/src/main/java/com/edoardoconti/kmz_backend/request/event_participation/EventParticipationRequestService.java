package com.edoardoconti.kmz_backend.request.event_participation;

import com.edoardoconti.kmz_backend.content.event.EventService;
import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.security.AuthService;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventParticipationRequestService extends RequestService {
    private final EventParticipationRequestRepository eventParticipationRequestRepository;
    private final UserService userService;
    private final EventService eventService;
    private final AuthService authService;


    public List<EventParticipationResponseDto> getEventParticipationRequests() {
        var requests = this.eventParticipationRequestRepository.findAll();
        return requests
                .stream()
                .map(r -> EventParticipationMapper.toDto(
                            r,
                            eventService.getEvent(r.getEventId()),
                            userService.getUser(r.getGuestId())
                        )
                ).toList();
    }

    public List<EventParticipationResponseDto> getMyEventParticipationRequests() {
        var currentUser = this.authService.getCurrentUser();
        var requests = this.eventParticipationRequestRepository.findByGuestId(currentUser.getId());
        return requests
                .stream()
                .map(r -> EventParticipationMapper.toDto(
                            r,
                            eventService.getEvent(r.getEventId()),
                            userService.getUser(r.getGuestId())
                        )
                ).toList();
    }



    public EventParticipationResponseDto createEventParticipationRequest(Long eventId, Long guestId) {
        var event = this.eventService.getEvent(eventId);
        if(event == null)
            throw new IllegalArgumentException("Event not found");
        var guest = this.userService.getUser(guestId);
        if(guest == null)
            throw new IllegalArgumentException("User not found");
        var request = new EventParticipationRequest(event.getId(), authService.getCurrentUser().getId(), guest.getId());
        this.eventParticipationRequestRepository.save(request);
        return EventParticipationMapper.toDto(request, event, guest);
    }


    @Override
    public void approveRequest(Long requestId, String message) {

    }

    @Override
    public void rejectRequest(Long requestId, String message) {

    }
}
