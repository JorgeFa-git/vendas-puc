package com.example.vendas.sale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Subselect(value = """
SELECT
	s.id,
	c.id AS client_id,
	c."name" AS client_name,
	s.total AS total_value,
	s.created_at,
	(SELECT count(*) FROM sale_products sp WHERE sp.sale_id = s.id) AS total_products
FROM sale s
JOIN client c ON c.id = s.client_id
""")
@Data
@Entity
@Immutable
public class SaleOverview {
    @Id
    private Long id;

    private Long clientId;
    private String clientName;
    private BigDecimal totalValue;
    private OffsetDateTime createdAt;
    private Integer totalProducts;
}
