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

import com.vitorbionic.data.dto.PriceDTO;
import com.vitorbionic.services.postgres.temporal.PriceService;

@RestController
@RequestMapping(value = "/api/temporal/price")
public class PriceController {

    @Autowired
    private PriceService service;
    
    @GetMapping
    public List<PriceDTO> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public PriceDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public PriceDTO create(@RequestBody PriceDTO rental) {
        return service.create(rental);
    }
    
    @PutMapping
    public PriceDTO update(@RequestBody PriceDTO rental) {
        return service.update(rental);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
