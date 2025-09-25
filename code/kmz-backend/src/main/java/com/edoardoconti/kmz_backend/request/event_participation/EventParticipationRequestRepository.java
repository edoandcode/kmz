package com.edoardoconti.kmz_backend.request.event_participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipationRequestRepository extends JpaRepository<EventParticipationRequest, Long> {
}
