package com.edoardoconti.kmz_backend.request.content_publication;

import com.edoardoconti.kmz_backend.content.Content;


public class ContentPublicationMapper {

    public static ContentPublicationResponseDto toDto(ContentPublicationRequest request, Content content) {
        var responseDto = new ContentPublicationResponseDto();
        responseDto.setId(request.getId());
        responseDto.setContent(content);
        responseDto.setCreatedAt(request.getCreatedAt());
        responseDto.setProcessedAt(request.getProcessedAt());
        responseDto.setStatus(request.getStatus());
        responseDto.setMessage(request.getMessage());
        responseDto.setRequesterId(request.getRequesterId());
        responseDto.setRecipientId(request.getRecipientId());
        responseDto.setType(request.getType());
        return responseDto;
    }
}
