package com.edoardoconti.kmz_backend.content;

import java.util.List;

import com.edoardoconti.kmz_backend.event.EventContentDTO;
import com.edoardoconti.kmz_backend.event.EventService;
import com.edoardoconti.kmz_backend.process.ProcessContentDTO;
import com.edoardoconti.kmz_backend.process.ProcessService;
import com.edoardoconti.kmz_backend.product.ProductContentDTO;
import com.edoardoconti.kmz_backend.product.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@Tag(name="Contents")
public class ContentController {

    private final ProductService productService;
    private final ProcessService processService;
    private final EventService eventService;
    private final ContentRequestService contentRequestService;


    // PRODUCT
    @GetMapping("/products")
    public ResponseEntity<List<ProductContentDTO>> getProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

   @GetMapping("/products/{id}")
    public ResponseEntity<ProductContentDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProduct(id));
   }

   @PostMapping("/products")
    public ResponseEntity<ProductContentDTO> uploadProduct(@RequestBody @Valid ProductContentDTO productContentDto) {
        var savedProduct = this.productService.uploadProduct(productContentDto);
        contentRequestService.addRequest(savedProduct.getAuthorId(), savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
   }

    // PROCESS
    @GetMapping("/processes")
    public ResponseEntity<List<ProcessContentDTO>> getProcesses() {
        return ResponseEntity.ok(this.processService.getProcesses());
    }

    @GetMapping("/processes/{id}")
    public ResponseEntity<ProcessContentDTO> getProcess(@PathVariable Long id) {
        return ResponseEntity.ok(this.processService.getProcess(id));
    }

    @PostMapping("/processes")
    public ResponseEntity<ProcessContentDTO> uploadProcess(@RequestBody @Valid ProcessContentDTO processContentDto) {
        var savedProcess = this.processService.uploadProcess(processContentDto);
        contentRequestService.addRequest(savedProcess.getAuthorId(), savedProcess.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProcess);
    }

    // EVENTS
    @GetMapping("/events")
    public ResponseEntity<List<EventContentDTO>> getEvents() {
        return ResponseEntity.ok(this.eventService.getEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventContentDTO> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getEvent(id));
    }

    @PostMapping("/events")
    public ResponseEntity<EventContentDTO> uploadEvent(@RequestBody @Valid EventContentDTO eventContentDto) {
        var savedEvent = this.eventService.uploadEvent(eventContentDto);
        contentRequestService.addRequest(savedEvent.getAuthorId(), savedEvent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

}