package com.example.vendas.sale.model;

import lombok.Data;

import java.util.List;

@Data
public class SaleCreation {
    private Long clientId;
    private List<Long> productIds;
}
