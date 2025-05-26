package com.vitorbionic.services.objectdb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitorbionic.data.dto.FilmDTO;
import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.objectdb.Film;
import com.vitorbionic.repository.objectdb.ObjectdbFilmRepository;

@Service
public class ObjectdbFilmService {
    
    @Autowired
    private ObjectdbFilmRepository repository;
    
    private static final Logger logger = Logger.getLogger(ObjectdbFilmService.class.getName());
    
    public List<FilmDTO> findAll() {
        
        logger.info("Finding all films!");
        
        List<Film> entities = repository.findAll();
        
        List<FilmDTO> dtos = new ArrayList<>();
        for (Film entity : entities) {
            dtos.add(new FilmDTO(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getDescription(),
                    entity.getReleaseYear(),
                    entity.getGenre(),
                    entity.getDuration(),
                    entity.getCurrentPrice())
            );
        }
        
        return dtos;
    }
    
    public FilmDTO findById(Long id) {
        
        logger.info("Finding one film!");
        
        Film entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return new FilmDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getReleaseYear(),
                entity.getGenre(),
                entity.getDuration(),
                entity.getCurrentPrice()
                );
    }
    
    public FilmDTO create(FilmDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Creating one film!");
        
        Film entity = repository.save(new Film(
                null,
                dto.title(),
                dto.description(),
                dto.releaseYear(),
                dto.genre(),
                dto.duration(),
                dto.currentPrice()
                ));
        
        return new FilmDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getReleaseYear(),
                entity.getGenre(),
                entity.getDuration(),
                entity.getCurrentPrice()
                );
    }
    
    @Transactional("objectdbTransactionManager")
    public FilmDTO update(FilmDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Updating one film!");
        
        Film persistedFilm = repository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedFilm.setTitle(dto.title());
        persistedFilm.setDescription(dto.description());
        persistedFilm.setReleaseYear(dto.releaseYear());
        persistedFilm.setGenre(dto.genre());
        persistedFilm.setDuration(dto.duration());
        persistedFilm.setCurrentPrice(dto.currentPrice());
        
        logger.info("DTO hash: {" + System.identityHashCode(dto) + "}");
        logger.info("Persisted hash: {" + System.identityHashCode(persistedFilm) + "}");
        
        Film updatedFilm = repository.save(persistedFilm);
        
        return new FilmDTO(
                updatedFilm.getId(),
                updatedFilm.getTitle(),
                updatedFilm.getDescription(),
                updatedFilm.getReleaseYear(),
                updatedFilm.getGenre(),
                updatedFilm.getDuration(),
                updatedFilm.getCurrentPrice()
                );
    }
    
    @Transactional("objectdbTransactionManager")
    public void delete(Long id) {
        
        logger.info("Deleting one film!");
        
        Film film = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(film);
    }
}
