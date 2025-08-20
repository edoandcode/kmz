package com.edoardoconti.kmz_backend.event;

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
public class EventContentDTO {

    private Long id; // inherited from Content (assuming Content has an ID field)
    private Long authorId;

    private String name;
    private String location;
    private Date date;
    private List<Long> guestsIds;
}
