package com.edoardoconti.kmz_backend.process;

import com.edoardoconti.kmz_backend.content.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="processes")
@Getter
@Setter
public class Process extends Content {
    private String name;
    private String description;
    private String[] certifications ;
}
