package com.example.vendas.sale.model;

import com.example.vendas.client.model.Client;
import com.example.vendas.product.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Entity
@Table
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "sale_products",
            joinColumns = @JoinColumn(name = "sale_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", nullable = false)
    )
    private List<Product> products;

    @Column(nullable = false)
    private BigDecimal total;

    @Column
    @CreationTimestamp
    private OffsetDateTime createdAt;

}
