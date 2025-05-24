package com.vitorbionic.services.postgres.warehouse;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.warehouse.Film;
import com.vitorbionic.repository.postgres.warehouse.FilmRepository;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;
    
    private static final Logger logger = Logger.getLogger(FilmService.class.getName());
    
    public List<Film> findAll() {
        
        logger.info("Finding all film dimension registries!");
        
        return repository.findAll();
    }
    
    public Film findById(Long id) {
        
        logger.info("Finding one film dimension registry!");
        
        Film film = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return film;
    }
    
    public Film create(Film film) {
        if (film == null)
            throw new RequiredObjectIsNullException();
        film.setId(null);
        
        logger.info("Creating one film dimension registry!");        
        
        return repository.save(film);
    }
    
    public Film update(Film film) {
        if (film == null)
            throw new RequiredObjectIsNullException();
        
        logger.info("Updating one film dimension registry!");    
        
        Film persistedFilm = repository.findById(film.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedFilm.setTitle(film.getTitle());
        persistedFilm.setGenre(film.getGenre());
        persistedFilm.setDuration(film.getDuration());
        
        return repository.save(persistedFilm);
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one film dimension registry!");
        
        Film film = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(film);
    }
}
