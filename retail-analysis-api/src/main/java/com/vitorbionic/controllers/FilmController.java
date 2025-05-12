package com.vitorbionic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorbionic.model.objectdb.Film;
import com.vitorbionic.services.FilmService;

@RestController
@RequestMapping("/api/film")
public class FilmController {
    
    @Autowired
    private FilmService service;

    @GetMapping
    public List<Film> findAll() {
        return service.findAll();
    }
    
    @PostMapping
    public Film create(@RequestBody Film film) {
        return service.create(film);
    }
}
