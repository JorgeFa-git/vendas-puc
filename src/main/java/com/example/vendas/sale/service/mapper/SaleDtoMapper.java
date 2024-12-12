package com.example.vendas.sale.service.mapper;

import com.example.vendas.client.service.mapper.ClientDtoMapper;
import com.example.vendas.core.mapper.DtoMapper;
import com.example.vendas.sale.model.Sale;
import com.example.vendas.sale.model.SaleDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleDtoMapper implements DtoMapper<SaleDto, Sale> {
    private final ClientDtoMapper clientDtoMapper;

    public SaleDtoMapper(ClientDtoMapper clientDtoMapper) {
        this.clientDtoMapper = clientDtoMapper;
    }

    @Override
    public SaleDto domainToDto(Sale domain) {
        return SaleDto.builder()
                .id(domain.getId())
                .client(clientDtoMapper.domainToDto(domain.getClient()))
                .products(domain.getProducts().stream().toList())
                .total(domain.getTotal())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    @Override
    public List<SaleDto> domainToDto(List<Sale> domains) {
        return domains.stream().map(this::domainToDto).toList();
    }
}
