package com.example.vendas.sale.rest;

import com.example.vendas.sale.model.SaleCreation;
import com.example.vendas.sale.model.SaleDto;
import com.example.vendas.sale.model.SaleOverview;
import com.example.vendas.sale.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("sale")
public class SaleResource {

    private final SaleService saleService;

    public SaleResource(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/sales")
    public ResponseEntity<Page<SaleOverview>> getAllSales(Pageable pageable) {
        return ResponseEntity.ok(saleService.getAllSales(pageable));
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<SaleDto> getSale(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getById(id));
    }

    @PostMapping("/sales")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleCreation saleCreation) {
        return ResponseEntity.created(URI.create("")).body(saleService.create(saleCreation));
    }
}
