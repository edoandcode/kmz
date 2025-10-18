package com.edoardoconti.kmz_backend.request.content_publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentPublicationRequestRepository extends JpaRepository<ContentPublicationRequest, Long> { }
