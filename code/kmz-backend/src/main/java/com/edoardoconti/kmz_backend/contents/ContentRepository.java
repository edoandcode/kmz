package com.edoardoconti.kmz_backend.contents;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ContentRepository {

    private final List<Content> contents = new ArrayList<>();
    private Long nextId = 1L;


    public List<Content> findByType(ContentType contentType) {
        return contents.stream()
                .filter(c -> c.getType() == contentType)
                .toList();
    }


    public Content findById(Long id) {
        return contents.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public void save(Content content) {
        content.setId(nextId++);
        contents.add(content);
    }
}
