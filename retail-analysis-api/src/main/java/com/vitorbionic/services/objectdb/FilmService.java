package com.vitorbionic.services.objectdb;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.objectdb.Film;
import com.vitorbionic.repository.objectdb.FilmRepository;

@Service
public class FilmService {
    
    @Autowired
    private FilmRepository repository;
    
    private Logger logger = Logger.getLogger(FilmService.class.getName());
    
    public List<Film> findAll() {
        
        logger.info("Finding all films!");
        
        return repository.findAll();
    }
    
    public Film findById(Long id) {
        
        logger.info("Finding one film!");
        
        Film film = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return film;
    }
    
    public Film create(Film film) {
        if (film == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Creating one film!");
        
        return repository.save(film);
    }
    
    public Film update(Film film) {
        if (film == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Updating one film!");
        
        Film persistedFilm = repository.findById(film.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedFilm.setTitle(film.getTitle());
        persistedFilm.setDescription(film.getDescription());
        persistedFilm.setReleaseYear(film.getReleaseYear());
        persistedFilm.setGenre(film.getGenre());
        persistedFilm.setDuration(film.getDuration());
        persistedFilm.setCurrentPrice(film.getCurrentPrice());
        
        return repository.save(persistedFilm);
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one film!");
        
        Film film = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(film);
    }
}
