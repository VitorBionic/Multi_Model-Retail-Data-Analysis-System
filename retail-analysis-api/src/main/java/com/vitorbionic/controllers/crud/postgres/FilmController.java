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

import com.vitorbionic.model.postgres.warehouse.Film;
import com.vitorbionic.services.postgres.warehouse.FilmService;

@RestController
@RequestMapping(value = "/api/dw/dim/film")
public class FilmController {

    @Autowired
    private FilmService service;

    @GetMapping
    public List<Film> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Film findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public Film create(@RequestBody Film film) {
        return service.create(film);
    }
    
    @PutMapping
    public Film update(@RequestBody Film film) {
        return service.update(film);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
