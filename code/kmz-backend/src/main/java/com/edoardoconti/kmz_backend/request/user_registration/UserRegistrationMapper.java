package com.edoardoconti.kmz_backend.request.user_registration;


public class UserRegistrationMapper {

    public static UserRegistrationResponseDto toDto(UserRegistrationRequest request) {
        var responseDto = new UserRegistrationResponseDto();
        responseDto.setId(request.getId());
        responseDto.setUserId(request.getUser().getId());
        responseDto.setUserFirstName(request.getUser().getFirstName());
        responseDto.setUserLastName(request.getUser().getLastName());
        responseDto.setUserEmail(request.getUser().getEmail());
        responseDto.setRequestedRole(request.getRequestedRole());
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

