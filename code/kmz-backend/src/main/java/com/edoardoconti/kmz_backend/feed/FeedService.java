package com.edoardoconti.kmz_backend.feed;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedItemRepository feedItemRepository;
    private final FeedItemMapper feedItemMapper;
    private final ContentService contentService;


    public void publishContent(Content content) {
        var feedItem = new FeedItem(content);
        this.feedItemRepository.save(feedItem);
    }

    public void unpublishContent(Long contentId) {
        var content = this.contentService.getContent(contentId);
        if (content == null)
            return;
        var feedItem = this.feedItemRepository.findByContent(content);
        if (feedItem != null)
            this.feedItemRepository.delete(feedItem);
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
