package com.edoardoconti.kmz_backend.process;

import java.util.List;

import com.edoardoconti.kmz_backend.process.Process;
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
    public ResponseEntity<List<Process>> getProducts() {
        return ResponseEntity.ok(this.service.getProcesses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Process> getProcesses(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getProcess(id));
    }

    @PostMapping
    public ResponseEntity<Void> uploadProcess(@RequestBody Process process) {
        this.service.uploadProcess(process);
        return ResponseEntity.status(201).build();
    }


}
