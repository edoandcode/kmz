package com.edoardoconti.kmz_backend.content.processed;

import com.edoardoconti.kmz_backend.content.ContentStatus;
import com.edoardoconti.kmz_backend.feed.FeedService;
import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProcessedProductService implements ProcessedProductService {

    private final ProcessedProductRepository repository;
    private final ProcessedProductContentMapper processedProductContentMapper;
    private final AuthService authService;
    private final FeedService feedService;

    @Override
    public ProcessedProductContentDto uploadProcessedProduct(ProcessedProductContentDto productContentDto) {
        var product = this.processedProductContentMapper.toEntity(productContentDto);
        var currentUser = this.authService.getCurrentUser();
        product.setStatus(ContentStatus.DRAFT);
        product.setAuthorId(currentUser.getId());
        this.repository.save(product);
        return this.processedProductContentMapper.toDto(product);
    }

    @Override
    public List<ProcessedProductContentDto> getProcessedProducts() {
        return this.repository.findAll()
                .stream()
                .map(this.processedProductContentMapper::toDto)
                .toList();
    }


    @Override
    public ProcessedProductContentDto getProcessedProduct(Long id) {
        var product = this.repository.findById(id).orElse(null);
        return this.processedProductContentMapper.toDto(product);
    }

    @Override
    public List<ProcessedProductContentDto> getMyProcessedProducts() {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .map(this.processedProductContentMapper::toDto)
                .toList();
    }

    @Override
    public ProcessedProductContentDto getMyProcessedProduct(Long id) {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(this.processedProductContentMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProcessedProductContentDto deleteProcessedProduct(Long id) {
        var product = this.repository.findById(id).orElse(null);
        var dto = this.processedProductContentMapper.toDto(product);
        if(product == null)
            return null;
        var currentUser = this.authService.getCurrentUser();
        if(!product.getAuthorId().equals(currentUser.getId()))
            return null;
        this.feedService.unpublishContent(product.getId());
        this.repository.delete(product);
        return dto;
    }
}
