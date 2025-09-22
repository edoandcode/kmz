package com.edoardoconti.kmz_backend.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public List<Content> getContents() {
        return this.contentRepository.findAll();
    }

    public Content getContent(Long id) {
        return this.contentRepository.findById(id).orElse(null);
    }
}
