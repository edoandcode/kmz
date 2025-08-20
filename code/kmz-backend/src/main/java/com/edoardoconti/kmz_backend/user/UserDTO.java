package com.edoardoconti.kmz_backend.user;

import jakarta.validation.constraints.*;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO for transferring User data without exposing JPA entities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "First name must not be blank")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    private Set<UserRoleType> roles;
}