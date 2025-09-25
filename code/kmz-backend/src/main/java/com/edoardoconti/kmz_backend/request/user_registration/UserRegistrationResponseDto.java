package com.edoardoconti.kmz_backend.request.user_registration;

import com.edoardoconti.kmz_backend.request.RequestStatus;
import com.edoardoconti.kmz_backend.request.RequestType;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserRegistrationResponseDto {

    private Long id;

    private Long userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private UserRole requestedRole;

    private Date createdAt;

    private Date processedAt;

    private RequestStatus status;

    private String message;

    private Long requesterId;

    private Long recipientId;

    private RequestType type;

}
