package com.edoardoconti.kmz_backend.request.event_participation;

import com.edoardoconti.kmz_backend.content.event.EventContentDto;
import com.edoardoconti.kmz_backend.user.UserDto;

public class EventParticipationMapper {

    public static EventParticipationResponseDto toDto(
            EventParticipationRequest request,
            EventContentDto event,
            UserDto guest
    ) {

        EventParticipationResponseDto responseDto = new EventParticipationResponseDto();
        responseDto.setId(request.getId());
        responseDto.setGuestFirstName(guest.getFirstName());
        responseDto.setGuestLastName(guest.getLastName());
        responseDto.setGuestEmail(guest.getEmail());
        responseDto.setEvent(event);
        responseDto.setCreatedAt(request.getCreatedAt());
        responseDto.setProcessedAt(request.getProcessedAt());
        responseDto.setStatus(request.getStatus());
        responseDto.setMessage(request.getMessage());
        responseDto.setRequesterId(request.getRequesterId());
        responseDto.setRecipientId(request.getRecipientId());
        responseDto.setType(request.getType());

        return responseDto;
    }
}
