package com.edoardoconti.kmz_backend.content.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventContent, Long> {
    List<EventContent> findAllByAuthorId(Long authorId);
}