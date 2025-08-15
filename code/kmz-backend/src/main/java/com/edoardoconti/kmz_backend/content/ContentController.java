package com.edoardoconti.kmz_backend.content;

import java.util.List;

import com.edoardoconti.kmz_backend.event.Event;
import com.edoardoconti.kmz_backend.event.EventService;
import com.edoardoconti.kmz_backend.process.ProcessService;
import com.edoardoconti.kmz_backend.product.Product;
import com.edoardoconti.kmz_backend.product.ProductService;
import com.edoardoconti.kmz_backend.process.Process;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ProductService productService;
    private final ProcessService processService;
    private final EventService eventService;
    private final ContentRequestService contentRequestService;


    // PRODUCT
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

   @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProduct(id));
   }

   @PostMapping("/products")
    public ResponseEntity<Product> uploadProduct(@RequestBody Product product) {
        var savedProduct = this.productService.uploadProduct(product);
        contentRequestService.addRequest(savedProduct.getAuthorId(), savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
   }

    // PROCESS
    @GetMapping("/processes")
    public ResponseEntity<List<Process>> getProcesses() {
        return ResponseEntity.ok(this.processService.getProcesses());
    }

    @GetMapping("/processes/{id}")
    public ResponseEntity<Process> getProcess(@PathVariable Long id) {
        return ResponseEntity.ok(this.processService.getProcess(id));
    }

    @PostMapping("/processes")
    public ResponseEntity<Process> uploadProcess(@RequestBody Process process) {
        var savedProcess = this.processService.uploadProcess(process);
        contentRequestService.addRequest(savedProcess.getAuthorId(), savedProcess.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProcess);
    }

    // EVENTS
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.ok(this.eventService.getEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getEvent(id));
    }

    @PostMapping("/events")
    public ResponseEntity<Event> uploadEvent(@RequestBody Event event) {
        var savedEvent = this.eventService.uploadEvent(event);
        contentRequestService.addRequest(savedEvent.getAuthorId(), savedEvent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

}