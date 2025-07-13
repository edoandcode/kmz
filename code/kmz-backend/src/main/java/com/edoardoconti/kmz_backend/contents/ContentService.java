package com.edoardoconti.kmz_backend.contents;

import java.util.List;

public interface ContentService {
    void uploadContent(Content content);
    List<Content> fetchContents(ContentType type);
    List<Content> fetchAllContents();
    Content fetchContent(Long id);
}
