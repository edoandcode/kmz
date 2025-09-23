package com.edoardoconti.kmz_backend.feed;

import com.edoardoconti.kmz_backend.content.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedItemRepository feedItemRepository;

    public void publishContent(Content content) {
        var feedItem = new FeedItem(content);
        this.feedItemRepository.save(feedItem);
    }
    




}
