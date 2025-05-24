package com.vitorbionic.controllers.crud.postgres.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorbionic.model.postgres.warehouse.Client;
import com.vitorbionic.services.postgres.warehouse.ClientService;

@RestController
@RequestMapping(value = "/api/dw/dim/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Client findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public Client create(@RequestBody Client client) {
        return service.create(client);
    }
    
    @PutMapping
    public Client update(@RequestBody Client client) {
        return service.update(client);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
