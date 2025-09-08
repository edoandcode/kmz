package com.edoardoconti.kmz_backend.content;

import java.util.List;

import com.edoardoconti.kmz_backend.event.EventContentDto;
import com.edoardoconti.kmz_backend.event.EventService;
import com.edoardoconti.kmz_backend.process.ProcessContentDto;
import com.edoardoconti.kmz_backend.process.ProcessService;
import com.edoardoconti.kmz_backend.product.ProductContentDto;
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
  //  private final ContentRequestService contentRequestService;


    // PRODUCT
    @GetMapping("/products")
    public ResponseEntity<List<ProductContentDto>> getProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

   @GetMapping("/products/{id}")
    public ResponseEntity<ProductContentDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProduct(id));
   }

   @PostMapping("/products")
    public ResponseEntity<ProductContentDto> uploadProduct(@RequestBody @Valid ProductContentDto productContentDto) {
        var savedProduct = this.productService.uploadProduct(productContentDto);
       // contentRequestService.addRequest(savedProduct.getAuthorId(), savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
   }

    // PROCESS
    @GetMapping("/processes")
    public ResponseEntity<List<ProcessContentDto>> getProcesses() {
        return ResponseEntity.ok(this.processService.getProcesses());
    }

    @GetMapping("/processes/{id}")
    public ResponseEntity<ProcessContentDto> getProcess(@PathVariable Long id) {
        return ResponseEntity.ok(this.processService.getProcess(id));
    }

    @PostMapping("/processes")
    public ResponseEntity<ProcessContentDto> uploadProcess(@RequestBody @Valid ProcessContentDto processContentDto) {
        var savedProcess = this.processService.uploadProcess(processContentDto);
      //  contentRequestService.addRequest(savedProcess.getAuthorId(), savedProcess.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProcess);
    }

    // EVENTS
    @GetMapping("/events")
    public ResponseEntity<List<EventContentDto>> getEvents() {
        return ResponseEntity.ok(this.eventService.getEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventContentDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getEvent(id));
    }

    @PostMapping("/events")
    public ResponseEntity<EventContentDto> uploadEvent(@RequestBody @Valid EventContentDto eventContentDto) {
        var savedEvent = this.eventService.uploadEvent(eventContentDto);
      //  contentRequestService.addRequest(savedEvent.getAuthorId(), savedEvent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

}