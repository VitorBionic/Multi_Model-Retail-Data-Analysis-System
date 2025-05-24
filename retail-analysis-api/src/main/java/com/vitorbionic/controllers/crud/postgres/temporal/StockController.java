package com.vitorbionic.controllers.crud.postgres.temporal;

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

import com.vitorbionic.data.dto.StockDTO;
import com.vitorbionic.services.postgres.temporal.StockService;

@RestController
@RequestMapping(value = "/api/temporal/stock")
public class StockController {

    @Autowired
    private StockService service;
    
    @GetMapping
    public List<StockDTO> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public StockDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public StockDTO create(@RequestBody StockDTO stock) {
        return service.create(stock);
    }
    
    @PutMapping
    public StockDTO update(@RequestBody StockDTO stock) {
        return service.update(stock);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
