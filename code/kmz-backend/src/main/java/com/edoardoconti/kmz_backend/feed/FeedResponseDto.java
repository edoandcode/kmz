package com.edoardoconti.kmz_backend.feed;

import com.edoardoconti.kmz_backend.content.Content;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseDto {

    private Long id;

    @NotNull
    private Date publishedAt;

    @NotNull
    private Content content;
}
