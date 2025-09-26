package com.edoardoconti.kmz_backend.request.event_participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventParticipationRequestRepository extends JpaRepository<EventParticipationRequest, Long> {
    List<EventParticipationRequest> findByGuestId(Long guestId);
}
