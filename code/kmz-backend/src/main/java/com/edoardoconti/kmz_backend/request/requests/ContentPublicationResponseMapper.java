package com.edoardoconti.kmz_backend.request.requests;

public class ContentPublicationResponseMapper {
    public static ContentPublicationResponseDto toDto(ContentPublicationRequest request) {
        var responseDto = new ContentPublicationResponseDto();
        responseDto.setId(request.getId());
        responseDto.setContentId(responseDto.getContentId());
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
