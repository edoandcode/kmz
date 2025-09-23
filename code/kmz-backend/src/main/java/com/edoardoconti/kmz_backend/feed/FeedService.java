package com.edoardoconti.kmz_backend.feed;

import com.edoardoconti.kmz_backend.content.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedItemRepository feedItemRepository;
    private final FeedItemMapper feedItemMapper;


    public void publishContent(Content content) {
        var feedItem = new FeedItem(content);
        this.feedItemRepository.save(feedItem);
    }


    public List<FeedResponseDto> getPublicContents() {
        return this.feedItemRepository.findAll()
                .stream()
                .map(this.feedItemMapper::toDto)
                .toList();
    }

    public FeedResponseDto getPublicContent(Long id) {
        return this.feedItemRepository.findById(id)
                .map(this.feedItemMapper::toDto)
                .orElse(null);
    }




}
