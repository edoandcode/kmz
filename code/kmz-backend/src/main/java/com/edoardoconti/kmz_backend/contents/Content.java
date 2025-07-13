package com.edoardoconti.kmz_backend.contents;

public class Content {
    private Long id;
    private ContentType type;
    private String author;

    public Content(Long id, ContentType type, String author) {
        this.id = id;
        this.type = type;
        this.author = author;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
