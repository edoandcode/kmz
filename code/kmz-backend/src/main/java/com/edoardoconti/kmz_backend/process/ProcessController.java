package com.edoardoconti.kmz_backend.process;

import java.util.List;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/processes")
public class ProcessController {
    private ProcessService service;

    public ProcessController(ProcessService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Content>> getProducts() {
        return ResponseEntity.ok(this.service.getProcesses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getProcess(id));
    }

    @PostMapping
    public ResponseEntity<Void> uploadProduct(@RequestBody Content content) {
        this.service.uploadProcess(content);
        return ResponseEntity.status(201).build();
    }


}
