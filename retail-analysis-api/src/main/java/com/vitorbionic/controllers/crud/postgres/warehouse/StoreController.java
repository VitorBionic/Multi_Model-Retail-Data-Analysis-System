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

import com.vitorbionic.model.postgres.warehouse.Store;
import com.vitorbionic.services.postgres.warehouse.StoreService;

@RestController
@RequestMapping(value = "/api/dw/dim/store")
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping
    public List<Store> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Store findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public Store create(@RequestBody Store store) {
        return service.create(store);
    }
    
    @PutMapping
    public Store update(@RequestBody Store store) {
        return service.update(store);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
