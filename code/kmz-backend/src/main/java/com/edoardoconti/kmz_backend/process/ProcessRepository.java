package com.edoardoconti.kmz_backend.process;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessContent, Long> {
    List<ProcessContent> findAllByAuthorId(Long authorId);
}