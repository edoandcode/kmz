package com.edoardoconti.kmz_backend.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void setContentStatus(Long id, ContentStatus status) {
        var content = this.contentRepository.findById(id).orElse(null);
        if (content == null)
            throw new NoSuchElementException("Content with id " + id + " not found");
        content.setStatus(status);
        this.contentRepository.save(content);
    }


}
