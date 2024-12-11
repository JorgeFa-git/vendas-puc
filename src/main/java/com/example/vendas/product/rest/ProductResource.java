package com.example.vendas.product.rest;

import com.example.vendas.product.model.ProductDTO;
import com.example.vendas.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> createProduct(@PathVariable Long id, @RequestBody ProductDTO productToCreate) {
        return ResponseEntity.created(URI.create("")).body(productService.updateProduct(id, productToCreate));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productToCreate) {
        return ResponseEntity.created(URI.create("")).body(productService.createProduct(productToCreate));
    }
}
