package com.example.vendas.sale.service;

import com.example.vendas.client.repository.ClientRepository;
import com.example.vendas.client.service.ClientService;
import com.example.vendas.product.model.Product;
import com.example.vendas.product.repository.ProductRepository;
import com.example.vendas.product.service.ProductService;
import com.example.vendas.sale.model.Sale;
import com.example.vendas.sale.model.SaleCreation;
import com.example.vendas.sale.model.SaleDto;
import com.example.vendas.sale.model.SaleOverview;
import com.example.vendas.sale.repository.SaleOverviewRepository;
import com.example.vendas.sale.repository.SaleRepository;
import com.example.vendas.sale.service.mapper.SaleDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@Transactional(readOnly = true)
public class SaleService {

    private final SaleOverviewRepository saleOverviewRepository;
    private final SaleRepository saleRepository;
    private final SaleDtoMapper saleDtoMapper;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final ProductService productService;

    public SaleService(SaleOverviewRepository saleOverviewRepository, SaleRepository saleRepository, SaleDtoMapper saleDtoMapper, ClientService clientService, ProductRepository productRepository, ClientRepository clientRepository, ProductService productService) {
        this.saleOverviewRepository = saleOverviewRepository;
        this.saleRepository = saleRepository;
        this.saleDtoMapper = saleDtoMapper;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.productService = productService;
    }

    public Page<SaleOverview> getAllSales(Pageable pageable) {
        return saleOverviewRepository.findAll(pageable);
    }

    public SaleDto getById(Long id) {
        return saleDtoMapper.domainToDto(saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada")));
    }

    @Transactional
    public SaleDto create(SaleCreation saleCreation) {
        Sale newSale = new Sale();
        newSale.setClient(clientRepository.findById(saleCreation.getClientId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado")));

        var products = productRepository.findAllById(saleCreation.getProductIds());
        productService.reduceProductStockByIds(saleCreation.getProductIds());
        newSale.setProducts(products);
        newSale.setTotal(products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        newSale.setCreatedAt(OffsetDateTime.now());

        return saleDtoMapper.domainToDto(saleRepository.saveAndFlush(newSale));
    }
}
