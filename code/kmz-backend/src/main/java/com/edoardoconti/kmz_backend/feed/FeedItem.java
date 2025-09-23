package com.edoardoconti.kmz_backend.feed;

import com.edoardoconti.kmz_backend.content.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
public class FeedItem {
    @Id
    @GeneratedValue
    private Long id;

    private Date publishedAt;

    @ManyToOne
    private Content content;

    public FeedItem(Content content) {
        this.content = content;
        this.publishedAt = new Date();
    }

}
