package com.vitorbionic.services.postgres.warehouse;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.warehouse.Store;
import com.vitorbionic.repository.postgres.warehouse.StoreRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository repository;
    
    private Logger logger = Logger.getLogger(StoreService.class.getName());
    
    public List<Store> findAll() {
        
        logger.info("Finding all store dimension registries!");
        
        return repository.findAll();
    }
    
    public Store findById(Long id) {
        
        logger.info("Finding one store dimension registry!");
        
        Store store = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return store;
    }
    
    public Store create(Store store) {
        if (store == null)
            throw new RequiredObjectIsNullException();
        store.setId(null);
        
        logger.info("Creating one store dimension registry!");        
        
        return repository.save(store);
    }
    
    public Store update(Store store) {
        if (store == null)
            throw new RequiredObjectIsNullException();
        
        logger.info("Updating one store dimension registry!");    
        
        Store persistedStore = repository.findById(store.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedStore.setName(store.getName());
        persistedStore.setCity(store.getCity());
        persistedStore.setState(store.getState());
        
        return repository.save(persistedStore);
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one store dimension registry!");
        
        Store store = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(store);
    }
}
