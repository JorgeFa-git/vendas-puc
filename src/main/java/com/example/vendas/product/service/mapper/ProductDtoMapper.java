package com.example.vendas.product.service.mapper;

import com.example.vendas.core.mapper.DtoMapper;
import com.example.vendas.product.model.Product;
import com.example.vendas.product.model.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDtoMapper implements DtoMapper<ProductDTO, Product> {

    @Override
    public ProductDTO domainToDto(Product domain) {
        return ProductDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .price(domain.getPrice())
                .stock(domain.getStock())
                .build();
    }

    @Override
    public List<ProductDTO> domainToDto(List<Product> domains) {
        return domains.stream().map(this::domainToDto).toList();
    }

    public Product dtoToDomain(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }
}
