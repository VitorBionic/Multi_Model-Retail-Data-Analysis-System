package com.vitorbionic.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.model.objectdb.Film;
import com.vitorbionic.repository.objectdb.FilmRepository;

@Service
public class FilmService {
    
    @Autowired
    private FilmRepository repository;
    
    private Logger logger = Logger.getLogger(FilmService.class.getName());
    
    public List<Film> findAll() {
        return repository.findAll();
    }
    
    public Film create(Film film) {
        return repository.save(film);
    }
}
