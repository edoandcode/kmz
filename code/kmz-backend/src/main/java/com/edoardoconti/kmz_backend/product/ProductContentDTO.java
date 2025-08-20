package com.edoardoconti.kmz_backend.product;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * DTO for transferring ProductContent data without exposing JPA entities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductContentDTO {
    private Long id; // inherited from Content (assuming Content has an ID field)

    @NotNull(message = "Author ID cannot be null")
    private Long authorId;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @PastOrPresent(message = "Sowing date cannot be in the future")
    private Date sowingDate;

    @FutureOrPresent(message = "Harvest date cannot be in the past")
    private Date harvestDate;

    @NotBlank(message = "Cultivation method is required")
    private String cultivationMethod;

    @Size(max = 10, message = "You can provide at most 10 certifications")
    private List<@NotBlank(message = "Certification name cannot be blank") String> certification;
}
