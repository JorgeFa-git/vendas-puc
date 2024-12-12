package com.example.vendas.product.repository;

import com.example.vendas.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @NativeQuery(value = "UPDATE product SET stock = stock - 1 WHERE id in (?1)")
    void reduceProductStockByIds(List<Long> productIds);
}
