package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProductService implements ProductService{

    private final ProductRepository repository;
    private final ProductContentMapper productContentMapper;
    private final AuthService authService;

    @Override
    public ProductContentDto uploadProduct(ProductContentDto productContentDto) {
        var product = this.productContentMapper.toEntity(productContentDto);
        var currentUser = this.authService.getCurrentUser();
        product.setAuthorId(currentUser.getId());
        this.repository.save(product);
        return this.productContentMapper.toDto(product);
    }

    @Override
    public List<ProductContentDto> getProducts() {
        return this.repository.findAll()
                .stream()
                .map(this.productContentMapper::toDto)
                .toList();
    }

    @Override
    public ProductContentDto getProduct(Long id) {
        var product = this.repository.findById(id).orElse(null);
        return this.productContentMapper.toDto(product);
    }

    @Override
    public List<ProductContentDto> getMyProducts() {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .map(this.productContentMapper::toDto)
                .toList();
    }

    @Override
    public ProductContentDto getMyProduct(Long id) {
        var currentUser = this.authService.getCurrentUser();
        return this.repository.findAllByAuthorId(currentUser.getId())
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(this.productContentMapper::toDto)
                .orElse(null);
    }
}
