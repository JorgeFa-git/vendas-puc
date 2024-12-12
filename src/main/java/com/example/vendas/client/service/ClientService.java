package com.example.vendas.client.service;

import com.example.vendas.client.model.Client;
import com.example.vendas.client.model.ClientDto;
import com.example.vendas.client.model.ClientOverview;
import com.example.vendas.client.repository.ClientOverviewRepository;
import com.example.vendas.client.repository.ClientRepository;
import com.example.vendas.client.service.mapper.ClientDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientOverviewRepository clientOverviewRepository;
    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

    public ClientService(ClientOverviewRepository clientOverviewRepository, ClientRepository clientRepository, ClientDtoMapper clientDtoMapper) {
        this.clientOverviewRepository = clientOverviewRepository;
        this.clientRepository = clientRepository;
        this.clientDtoMapper = clientDtoMapper;
    }

    public Page<ClientOverview> getAll(Pageable pageable) {
        return clientOverviewRepository.findAll(pageable);
    }

    public ClientDto getById(Long id) {
        return clientDtoMapper.domainToDto(clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
    }

    @Transactional
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client) {
        Client foundClient = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        foundClient.setName(client.getName());
        foundClient.setEmail(client.getEmail());
        foundClient.setPhone(client.getPhone());
        return clientRepository.save(foundClient);
    }
}
