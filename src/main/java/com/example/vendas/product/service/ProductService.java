package com.example.vendas.product.service;

import com.example.vendas.product.model.Product;
import com.example.vendas.product.model.ProductDTO;
import com.example.vendas.product.repository.ProductRepository;
import com.example.vendas.product.service.mapper.ProductDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    public ProductService(ProductRepository productRepository, ProductDtoMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.productDtoMapper = productDtoMapper;
    }

    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return new PageImpl<>(productDtoMapper.domainToDto(products.getContent()), pageable, products.getTotalElements());
    }

    public ProductDTO findById(Long id) {
        return productDtoMapper.domainToDto(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado")));
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productToCreate) {
        Product createdProduct = productRepository.saveAndFlush(productDtoMapper.dtoToDomain(productToCreate));
        return productDtoMapper.domainToDto(createdProduct);
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productToUpdate) {
        Product foundProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        foundProduct.setName(productToUpdate.getName());
        foundProduct.setPrice(productToUpdate.getPrice());
        foundProduct.setStock(productToUpdate.getStock());

        Product updatedProduct = productRepository.saveAndFlush(foundProduct);
        return productDtoMapper.domainToDto(updatedProduct);
    }
}
