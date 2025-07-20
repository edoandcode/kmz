package com.edoardoconti.kmz_backend.process;

import com.edoardoconti.kmz_backend.content.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Process extends Content {
    private String name;
    private String description;
    private String[] certifications ;
}
