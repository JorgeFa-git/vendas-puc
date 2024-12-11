package com.example.vendas.sale.rest;

import com.example.vendas.sale.model.SaleOverview;
import com.example.vendas.sale.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
