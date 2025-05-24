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

import com.vitorbionic.data.dto.RentalDTO;
import com.vitorbionic.services.postgres.warehouse.RentalService;


@RestController
@RequestMapping(value = "/api/dw/fact/rental")
public class RentalController {

    @Autowired
    private RentalService service;
    
    @GetMapping
    public List<RentalDTO> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public RentalDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public RentalDTO create(@RequestBody RentalDTO rental) {
        return service.create(rental);
    }
    
    @PutMapping
    public RentalDTO update(@RequestBody RentalDTO rental) {
        return service.update(rental);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
