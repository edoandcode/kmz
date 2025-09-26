package com.edoardoconti.kmz_backend.feed;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedItemRepository extends JpaRepository<FeedItem, Long> {
    FeedItem findByContent(Content content);
}
