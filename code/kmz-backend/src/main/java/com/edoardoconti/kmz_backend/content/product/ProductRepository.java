package com.edoardoconti.kmz_backend.content.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductContent, Long> {
    List<ProductContent> findAllByAuthorId(Long authorId);
}
