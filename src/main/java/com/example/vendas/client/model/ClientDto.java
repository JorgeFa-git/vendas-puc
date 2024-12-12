package com.example.vendas.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
