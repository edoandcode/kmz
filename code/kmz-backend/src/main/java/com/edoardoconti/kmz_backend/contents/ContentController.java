package com.edoardoconti.kmz_backend.contents;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/contents")
public class ContentController {

    private final RealContentService service;

    public ContentController(RealContentService contentService) {
        this.service = contentService;
    }

    @GetMapping
    public ResponseEntity<List<Content>> getContents(@RequestParam(value = "type", required = false) ContentType type) {
        if(type == null)
            return ResponseEntity.ok(this.service.fetchAllContents());
        return ResponseEntity.ok(this.service.fetchContents(type));
    }

   @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.fetchContent(id));
   }

   @PostMapping
    public ResponseEntity<Void> uploadContent(@RequestBody Content content) {
        this.service.uploadContent(content);
        return ResponseEntity.status(201).build();
   }

}