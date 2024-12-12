package com.example.vendas.sale.model;

import com.example.vendas.client.model.ClientDto;
import com.example.vendas.product.model.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class SaleDto {
    private Long id;
    private ClientDto client;
    private List<Product> products;
    private BigDecimal total;
    private OffsetDateTime createdAt;

}
