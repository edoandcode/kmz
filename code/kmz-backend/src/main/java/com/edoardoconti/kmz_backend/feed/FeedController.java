package com.edoardoconti.kmz_backend.feed;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/contents")
    public ResponseEntity<List<FeedResponseDto>> getPublicContents() {
        var contents = this.feedService.getPublicContents();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/contents/{id}")
    public ResponseEntity<FeedResponseDto> getPublicContent(@PathVariable  Long id) {
        var content = this.feedService.getPublicContent(id);
        if (content == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.ok(content);
    }

}
