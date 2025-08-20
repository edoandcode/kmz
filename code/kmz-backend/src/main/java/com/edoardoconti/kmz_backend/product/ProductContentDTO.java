package com.edoardoconti.kmz_backend.product;

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
    private Long authorId;

    private String name;
    private String description;
    private Date sowingDate;
    private Date harvestDate;

    private String cultivationMethod;
    private List<String> certification;
}
