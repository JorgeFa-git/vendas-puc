package com.example.vendas.client.repository;

import com.example.vendas.client.model.ClientOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOverviewRepository extends JpaRepository<ClientOverview, Long> {
}
