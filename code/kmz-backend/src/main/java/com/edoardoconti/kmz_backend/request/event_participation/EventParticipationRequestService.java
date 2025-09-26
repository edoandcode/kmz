package com.edoardoconti.kmz_backend.request.event_participation;

import com.edoardoconti.kmz_backend.content.event.EventService;
import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.security.AuthService;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public void approveRequest(Long requestId, String message)  throws RuntimeException {
        var request = this.eventParticipationRequestRepository.findById(requestId).orElse(null);
        var currentUser = this.authService.getCurrentUser();
        System.out.println("REQUEST TO BE APPROVED --> " + request);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        if(!currentUser.getId().equals(request.getGuestId()))
            throw new IllegalArgumentException("You can only approve requests for yourself");
        this.eventService.addGuest(request.getEventId(), request.getGuestId());
        var approvedRequest = this.getApprovedRequest(request, message);
        this.eventParticipationRequestRepository.save(approvedRequest);
    }

    @Override
    public void rejectRequest(Long requestId, String message) throws RuntimeException {
        var request = this.eventParticipationRequestRepository.findById(requestId).orElse(null);
        var currentUser = this.authService.getCurrentUser();
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        if(!currentUser.getId().equals(request.getGuestId()))
            throw new IllegalArgumentException("You can only approve requests for yourself");
        var rejectedRequest = this.getRejectedRequest(request, message);
        this.eventParticipationRequestRepository.save(rejectedRequest);
    }
}
