package com.edoardoconti.kmz_backend.request.event_participation;

import com.edoardoconti.kmz_backend.content.event.EventContent;
import com.edoardoconti.kmz_backend.content.event.EventContentDto;
import com.edoardoconti.kmz_backend.request.RequestStatus;
import com.edoardoconti.kmz_backend.request.RequestType;
import com.edoardoconti.kmz_backend.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EventParticipationResponseDto {
    private Long id;

    private String guestFirstName;

    private String guestLastName;

    private String guestEmail;

    private EventContentDto event;

    private Date createdAt;

    private Date processedAt;

    private RequestStatus status;

    private String message;

    private Long requesterId;

    private Long recipientId;

    private RequestType type;
}
