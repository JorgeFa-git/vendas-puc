package com.example.vendas.sale.repository;

import com.example.vendas.sale.model.SaleOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOverviewRepository extends JpaRepository<SaleOverview, Long> {
}
