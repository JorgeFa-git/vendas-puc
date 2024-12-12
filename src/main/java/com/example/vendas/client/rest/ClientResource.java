package com.example.vendas.client.rest;

import com.example.vendas.client.model.Client;
import com.example.vendas.client.model.ClientDto;
import com.example.vendas.client.model.ClientOverview;
import com.example.vendas.client.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client")
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<Page<ClientOverview>> getAllClients(Pageable pageable) {
        return ResponseEntity.ok(clientService.getAll(pageable));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody @Validated Client client) {
        return ResponseEntity.created(URI.create("")).body(clientService.create(client));
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody @Validated Client client) {
        return ResponseEntity.ok(clientService.update(id, client));
    }

}
