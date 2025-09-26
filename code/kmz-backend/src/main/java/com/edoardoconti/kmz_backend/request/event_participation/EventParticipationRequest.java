package com.edoardoconti.kmz_backend.request.event_participation;

import com.edoardoconti.kmz_backend.content.event.EventContent;
import com.edoardoconti.kmz_backend.request.Request;
import com.edoardoconti.kmz_backend.request.RequestType;
import com.edoardoconti.kmz_backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="event_participation_requests")
public class EventParticipationRequest extends Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message="eventId is required")
    @Valid
    private Long eventId;

    @NotNull(message="guest is required")
    @Valid
    @JoinColumn(name = "guest_id", nullable = false)
    private Long guestId;

    protected EventParticipationRequest() {
        super(RequestType.EVENT_PARTICIPATION, null);
    }

    public EventParticipationRequest( Long eventId, Long requesterId, Long recipientId) {
        super(RequestType.EVENT_PARTICIPATION, requesterId, recipientId);
        this.eventId = eventId;
        this.guestId = recipientId;
    }
}
