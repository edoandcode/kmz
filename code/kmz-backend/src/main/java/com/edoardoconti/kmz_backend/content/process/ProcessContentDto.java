package com.edoardoconti.kmz_backend.content.process;

import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.place.Place;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.List;

/**
 * DTO for transferring ProcessContent data without exposing JPA entities.
 */
@Data
@NoArgsConstructor
public class ProcessContentDto {

    private Long id; // inherited from Content (assuming Content has an ID field)

    private Long authorId;

    private ContentType type = ContentType.PROCESS;
    private ContentStatus status;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @Size(max = 10, message = "You can provide at most 10 certifications")
    private List<@NotBlank(message = "Certification name cannot be blank") String> certifications;

    @Valid
    private Place processingPlace;
}