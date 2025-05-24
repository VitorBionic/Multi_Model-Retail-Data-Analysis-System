package com.vitorbionic.services.mongo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.mongo.FilmImage;
import com.vitorbionic.repository.mongo.FilmImageRepository;
import com.vitorbionic.repository.postgres.warehouse.FilmRepository;

@Service
public class FilmImageService {

    @Autowired
    private FilmImageRepository repository;
    
    @Autowired
    private FilmRepository filmRepository;

    private static final Logger logger = Logger.getLogger(FilmImageService.class.getName());
    
    public List<FilmImage> findAll() {
        
        logger.info("Finding all film images!");
        
        return repository.findAll();
    }
    
    public FilmImage findById(String id) {
        
        logger.info("Finding one film image!");
        
        FilmImage filmImage = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return filmImage;
    }
    
    public FilmImage create(FilmImage filmImage) {
        if (filmImage == null)
            throw new RequiredObjectIsNullException();
        filmImage.setId(null);
        
        logger.info("Creating one film image!");
        
        filmRepository.findById(filmImage.getFilmId())
            .orElseThrow(() -> new ResourceNotFoundException("Film not found with ID: " + filmImage.getFilmId()));
        
        return repository.save(filmImage);
    }
    
    public FilmImage update(FilmImage filmImage) {
        if (filmImage == null)
            throw new RequiredObjectIsNullException();
        
        logger.info("Updating one film image!");    
        
        FilmImage persistedFilmImage = repository.findById(filmImage.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        filmRepository.findById(filmImage.getFilmId())
            .orElseThrow(() -> new ResourceNotFoundException("Film not found with ID: " + filmImage.getFilmId()));
        
        persistedFilmImage.setFilmId(filmImage.getFilmId());
        persistedFilmImage.setImageUrl(filmImage.getImageUrl());
        persistedFilmImage.setDescription(filmImage.getDescription());
        
        return repository.save(persistedFilmImage);
    }
    
    public void delete(String id) {
        
        logger.info("Deleting one film image!");
        
        FilmImage filmImage = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(filmImage);
    }
}
