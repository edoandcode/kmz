package com.edoardoconti.kmz_backend.request.user_registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegistrationRequestRepository extends JpaRepository<UserRegistrationRequest, Long> {
    List<UserRegistrationRequest> findByRequesterId(Long requesterId);
}
