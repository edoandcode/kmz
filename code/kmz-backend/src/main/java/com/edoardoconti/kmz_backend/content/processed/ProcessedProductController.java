package com.edoardoconti.kmz_backend.content.processed;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProcessedProductController {
    private final ProcessedProductService processedProductService;


    @GetMapping("/processed-products")
    public ResponseEntity<List<ProcessedProductContentDto>> getProcessesProducts() {
        return ResponseEntity.ok(this.processedProductService.getProcessedProducts());
    }

    @GetMapping("/processed-products/{id}")
    public ResponseEntity<ProcessedProductContentDto> getProcessedProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.processedProductService.getProcessedProduct(id));
    }

    @GetMapping("/processed-products/me")
    public ResponseEntity<List<ProcessedProductContentDto>> getMyProcessedProducts() {
        return ResponseEntity.ok(this.processedProductService.getMyProcessedProducts());
    }

    @GetMapping("/processed-products/me/{id}")
    public ResponseEntity<ProcessedProductContentDto> getMyProcessedProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.processedProductService.getMyProcessedProduct(id));
    }

    @PostMapping("/processed-products")
    public ResponseEntity<ProcessedProductContentDto> uploadProcessedProduct(@RequestBody @Valid ProcessedProductContentDto productContentDto) {
        var savedProduct = this.processedProductService.uploadProcessedProduct(productContentDto);
        // contentRequestService.addRequest(savedProduct.getAuthorId(), savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("/processed-products/{id}")
    public ResponseEntity<ProcessedProductContentDto> deleteProcessedProduct(@PathVariable Long id) {
        var deletedContent = this.processedProductService.deleteProcessedProduct(id);
        if(deletedContent == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedContent);
    }
}
