package com.edoardoconti.kmz_backend.content.processed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedProductRepository extends JpaRepository<ProcessedProductContent, Long> {
    List<ProcessedProductContent> findAllByAuthorId(Long authorId);
}
