package com.example.vendas.client.service.mapper;

import com.example.vendas.client.model.Client;
import com.example.vendas.client.model.ClientDto;
import com.example.vendas.core.mapper.DtoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDtoMapper implements DtoMapper<ClientDto, Client> {
    @Override
    public ClientDto domainToDto(Client domain) {
        return ClientDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .build();
    }

    @Override
    public List<ClientDto> domainToDto(List<Client> domains) {
        return domains.stream().map(this::domainToDto).toList();
    }
}
