package com.vitorbionic.services.postgres.warehouse;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.warehouse.Client;
import com.vitorbionic.repository.postgres.warehouse.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    private Logger logger = Logger.getLogger(ClientService.class.getName());
    
    public List<Client> findAll() {
        
        logger.info("Finding all client dimension registries!");
        
        return repository.findAll();
    }
    
    public Client findById(Long id) {
        
        logger.info("Finding one client dimension registry!");
        
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return client;
    }
    
    public Client create(Client client) {
        if (client == null)
            throw new RequiredObjectIsNullException();
        client.setId(null);
        
        logger.info("Creating one client dimension registry!");        
        
        return repository.save(client);
    }
    
    public Client update(Client client) {
        if (client == null)
            throw new RequiredObjectIsNullException();
        
        logger.info("Updating one client dimension registry!");    
        
        Client persistedClient = repository.findById(client.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedClient.setName(client.getName());
        persistedClient.setEmail(client.getEmail());
        persistedClient.setCity(client.getCity());
        persistedClient.setState(client.getState());
        
        return repository.save(persistedClient);
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one client dimension registry!");
        
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(client);
    }
}
