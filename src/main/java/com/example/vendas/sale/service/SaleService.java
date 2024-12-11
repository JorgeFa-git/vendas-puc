package com.example.vendas.sale.service;

import com.example.vendas.sale.model.Sale;
import com.example.vendas.sale.model.SaleOverview;
import com.example.vendas.sale.repository.SaleOverviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    private final SaleOverviewRepository saleOverviewRepository;

    public SaleService(SaleOverviewRepository saleOverviewRepository) {
        this.saleOverviewRepository = saleOverviewRepository;
    }

    public Page<SaleOverview> getAllSales(Pageable pageable) {
        return saleOverviewRepository.findAll(pageable);
    }
}
