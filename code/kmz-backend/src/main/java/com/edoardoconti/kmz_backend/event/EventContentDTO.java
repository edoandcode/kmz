package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.ContentType;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * DTO for transferring EventContent data without exposing JPA entities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventContentDto {

    private Long id; // inherited from Content (assuming Content has an ID field)

    private Long authorId;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must be at most 200 characters")
    private String location;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Event date cannot be in the past")
    private Date date;

    @Size(max = 100, message = "You can provide at most 100 guest IDs")
    private List<@NotNull(message = "Guest ID cannot be null") Long> guestsIds;

}