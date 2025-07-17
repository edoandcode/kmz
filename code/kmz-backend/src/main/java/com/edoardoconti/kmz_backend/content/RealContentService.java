package com.edoardoconti.kmz_backend.content;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class RealContentService implements ContentService {

    private final ContentRepository repository;


    public RealContentService(ContentRepository contentRepository) {
        this.repository = contentRepository;
    }

    @Override
    public List<Content> fetchContents(ContentType contentType) {
        return this.repository.findByType(contentType);
    }

    @Override
    public List<Content> fetchAllContents() {
            return Stream.of(ContentType.values())
                    .map(this.repository::findByType)
                    .flatMap(List::stream)
                    .toList();
    }

    @Override
    public Content fetchContent(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public  void uploadContent(Content content) {
        this.repository.save(content);
    }
}
