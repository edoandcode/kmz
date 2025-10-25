package com.edoardoconti.kmz_backend.content.process;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.place.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="processes")
@Getter
@Setter
public class ProcessContent extends Content {
    private String name;
    private String description;
    @ElementCollection
    private List<String> certifications;
    @Embedded
    private Place processingPlace;

    public ProcessContent() {
        super(ContentType.PROCESS);
    }
}
