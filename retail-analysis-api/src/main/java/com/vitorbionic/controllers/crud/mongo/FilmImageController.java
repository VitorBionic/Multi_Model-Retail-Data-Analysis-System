package com.vitorbionic.controllers.crud.mongo;

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

import com.vitorbionic.model.mongo.FilmImage;
import com.vitorbionic.services.mongo.FilmImageService;

@RestController
@RequestMapping(value = "/api/mongo/filmImage")
public class FilmImageController {
    
    @Autowired
    private FilmImageService service;
    
    @GetMapping
    public List<FilmImage> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public FilmImage findById(@PathVariable String id) {
        return service.findById(id);
    }
    
    @PostMapping
    public FilmImage create(@RequestBody FilmImage filmImage) {
        return service.create(filmImage);
    }
    
    @PutMapping
    public FilmImage update(@RequestBody FilmImage filmImage) {
        return service.update(filmImage);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
