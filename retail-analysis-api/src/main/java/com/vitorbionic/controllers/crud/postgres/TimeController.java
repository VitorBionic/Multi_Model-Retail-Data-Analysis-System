package com.vitorbionic.controllers.crud.postgres;

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

import com.vitorbionic.model.postgres.warehouse.Time;
import com.vitorbionic.services.postgres.warehouse.TimeService;

@RestController
@RequestMapping(value = "/api/dw/dim/time")
public class TimeController {

    @Autowired
    private TimeService service;

    @GetMapping
    public List<Time> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Time findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public Time create(@RequestBody Time time) {
        return service.create(time);
    }
    
    @PutMapping
    public Time update(@RequestBody Time time) {
        return service.update(time);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
