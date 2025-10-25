package com.edoardoconti.kmz_backend.content.product;
import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.place.Place;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * DTO for transferring ProcessedProductContent data without exposing JPA entities.
 */
@Data
@NoArgsConstructor
public class ProductContentDto {
    private Long id; // inherited from Content (assuming Content has an ID field)

    private Long authorId;

    private ContentType type = ContentType.PRODUCT;
    private ContentStatus status;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @PastOrPresent(message = "Sowing date cannot be in the future")
    private Date sowingDate;

    @PastOrPresent(message = "Harvest date cannot be in the future")
    private Date harvestDate;

    @Size(max = 500, message = "Cultivation method must be at most 500 characters")
    private String cultivationMethod;

    @Size(max = 10, message = "You can provide at most 10 certifications")
    private List<@NotBlank(message = "Certification name cannot be blank") String> certifications;

    @Valid
    private Place cultivationPlace;
}
