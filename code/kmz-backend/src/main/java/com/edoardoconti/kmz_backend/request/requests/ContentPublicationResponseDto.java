package com.edoardoconti.kmz_backend.request.requests;

import com.edoardoconti.kmz_backend.request.RequestStatus;
import com.edoardoconti.kmz_backend.request.RequestType;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class ContentPublicationResponseDto {

    private Long id;

    private Long contentId;

    private UserRole requestedRole;

    private Date createdAt;

    private Date processedAt;

    private RequestStatus status;

    private String message;

    private Long requesterId;

    private Long recipientId;

    private RequestType type;
}
