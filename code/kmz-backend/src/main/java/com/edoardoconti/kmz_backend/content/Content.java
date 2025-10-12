package com.edoardoconti.kmz_backend.content;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@NoArgsConstructor
abstract public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    private Long authorId;

    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    public Content(ContentType type) {
        this.type = type;
        this.status = ContentStatus.DRAFT;
    }
}
