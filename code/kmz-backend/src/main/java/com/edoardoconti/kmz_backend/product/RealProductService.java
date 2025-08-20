package com.edoardoconti.kmz_backend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProductService implements ProductService{

    private final ProductRepository repository;
    private final ProductContentMapper productContentMapper;

    @Override
    public ProductContentDTO uploadProduct(ProductContentDTO productContentDto) {
        var product = this.productContentMapper.toEntity(productContentDto);
        productContentDto.setId(product.getId());
        this.repository.save(product);
        return productContentDto;
    }

    @Override
    public List<ProductContentDTO> getProducts() {
        return this.repository.findAll()
                .stream()
                .map(this.productContentMapper::toDto)
                .toList();
    }

    @Override
    public ProductContentDTO getProduct(Long id) {
        var product = this.repository.findById(id).orElse(null);
        return this.productContentMapper.toDto(product);
    }
}
