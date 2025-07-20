package com.edoardoconti.kmz_backend.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract public class Content {
    private Long id;
    private ContentType type;
    private String author;
}
