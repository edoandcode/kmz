package com.edoardoconti.kmz_backend.request.requests;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentService;



public class ContentPublicationResponseMapper {

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
