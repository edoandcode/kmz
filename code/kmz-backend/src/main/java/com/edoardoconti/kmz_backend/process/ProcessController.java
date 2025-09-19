package com.edoardoconti.kmz_backend.process;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/processes")
    public ResponseEntity<List<ProcessContentDto>> getProcesses() {
        return ResponseEntity.ok(this.processService.getProcesses());
    }

    @GetMapping("/processes/{id}")
    public ResponseEntity<ProcessContentDto> getProcess(@PathVariable Long id) {
        return ResponseEntity.ok(this.processService.getProcess(id));
    }

    @GetMapping("/processes/me")
    public ResponseEntity<List<ProcessContentDto>> getMyProcesses() {
        return ResponseEntity.ok(this.processService.getMyProcesses());
    }

    @GetMapping("/processes/me/{id}")
    public ResponseEntity<ProcessContentDto> getMyProcess(@PathVariable Long id) {
        return ResponseEntity.ok(this.processService.getMyProcess(id));
    }


    @PostMapping("/processes")
    public ResponseEntity<ProcessContentDto> uploadProcess(@RequestBody @Valid ProcessContentDto processContentDto) {
        var savedProcess = this.processService.uploadProcess(processContentDto);
        //  contentRequestService.addRequest(savedProcess.getAuthorId(), savedProcess.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProcess);
    }




}
