package com.edoardoconti.kmz_backend.feed;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  FeedItemMapper {
    FeedResponseDto toDto(FeedItem feedItem);
}
