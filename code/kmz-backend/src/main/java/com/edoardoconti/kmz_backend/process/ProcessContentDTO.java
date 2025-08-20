package com.edoardoconti.kmz_backend.process;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for transferring ProcessContent data without exposing JPA entities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessContentDTO {

    private Long id; // inherited from Content (assuming Content has an ID field)
    private Long authorId;

    private String name;
    private String description;
    private List<String> certifications;

}
