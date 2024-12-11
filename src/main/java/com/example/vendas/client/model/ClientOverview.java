package com.example.vendas.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;


@Subselect(value = """
        SELECT
            c.id,
            c.name,
            c.email,
            c.phone,
            (
                SELECT 
                    count(s.id)
                FROM sale s 
                WHERE s.client_id = c.id
            ) AS total_sales
        FROM client c
        """
)
@Data
@Entity
@Immutable
public class ClientOverview {

    @Id
    private Long id;

    private String name;
    private String email;
    private String phone;
    private Integer totalSales;
}
