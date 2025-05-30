package com.vitorbionic.controllers.crud.objectdb;

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

import com.vitorbionic.data.dto.FilmDTO;
import com.vitorbionic.services.objectdb.ObjectdbFilmService;

@RestController
@RequestMapping("/api/odb/film")
public class ObjectdbFilmController {
    
    @Autowired
    private ObjectdbFilmService service;

    @GetMapping
    public List<FilmDTO> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public FilmDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PostMapping
    public FilmDTO create(@RequestBody FilmDTO film) {
        return service.create(film);
    }
    
    @PutMapping
    public FilmDTO update(@RequestBody FilmDTO film) {
        return service.update(film);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
