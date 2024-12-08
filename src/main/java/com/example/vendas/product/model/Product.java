package com.example.vendas.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private
}
