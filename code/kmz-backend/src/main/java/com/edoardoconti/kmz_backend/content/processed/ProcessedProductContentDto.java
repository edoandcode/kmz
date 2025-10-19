package com.edoardoconti.kmz_backend.content.processed;
import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.content.process.ProcessContentDto;
import com.edoardoconti.kmz_backend.content.product.ProductContentDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * DTO for transferring ProcessedProductContent data without exposing JPA entities.
 */
@Data
@NoArgsConstructor
public class ProcessedProductContentDto {

    private Long id; // inherited from Content (assuming Content has an ID field)

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Content type is required")
    private ContentType type = ContentType.PROCESSED_PRODUCT;

    @NotNull(message = "Content status is required")
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

    @NotNull(message = "Processes list cannot be null")
    @Size(min = 1, max = 10, message = "You must provide between 1 and 10 processes")
    private List<@Valid ProcessContentDto> processes;

    @NotNull(message = "Ingredients list cannot be null")
    @Size(min = 1, max = 50, message = "You must provide between 1 and 50 ingredients")
    private List<@Valid ProductContentDto> ingredients;

    @Size(max = 10, message = "You can provide at most 10 certifications")
    private List<@NotBlank(message = "Certification name cannot be blank") String> certifications;
}
