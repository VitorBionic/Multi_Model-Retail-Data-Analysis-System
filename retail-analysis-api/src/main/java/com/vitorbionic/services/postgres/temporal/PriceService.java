package com.vitorbionic.services.postgres.temporal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.vitorbionic.data.dto.PriceDTO;
import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.temporal.Price;
import com.vitorbionic.repository.postgres.temporal.PriceRepository;
import com.vitorbionic.repository.postgres.warehouse.FilmRepository;

public class PriceService {
    
    @Autowired
    private PriceRepository repository;
    
    @Autowired
    private FilmRepository filmRepository;
    
    private Logger logger = Logger.getLogger(PriceService.class.getName());
    
    public List<PriceDTO> findAll() {
        
        logger.info("Finding all historical price registries!");
        
        List<Price> entities = repository.findAll();
        
        List<PriceDTO> dtos = new ArrayList<>();
        
        for (Price entity : entities) {
            dtos.add(new PriceDTO(
                    entity.getId(),
                    entity.getFilm().getId(),
                    entity.getPrice(),
                    entity.getStartValidity(),
                    entity.getEndValidity())
            );
        }
        
        return dtos;
    }
    
    public PriceDTO findById(Long id) {
        
        logger.info("Finding one historical price registry!");
        
        Price entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return new PriceDTO(
                entity.getId(),
                entity.getFilm().getId(),
                entity.getPrice(),
                entity.getStartValidity(),
                entity.getEndValidity()
                );
    }
    
    public PriceDTO create(PriceDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Creating one historical price registry!");
        
        Price entity = repository.save(new Price(
                null,
                filmRepository.findById(dto.filmId())
                    .orElseThrow(() -> new ResourceNotFoundException("Film registry not found with ID: " + dto.filmId())),
                dto.price(),
                dto.startValidity(),
                dto.endValidity()
                ));
        
        return new PriceDTO(
                entity.getId(),
                entity.getFilm().getId(),
                entity.getPrice(),
                entity.getStartValidity(),
                entity.getEndValidity()
                );
    }
    
    public PriceDTO update(PriceDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Updating one historical price registry!");
        
        Price persistedPrice = repository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedPrice.setFilm(filmRepository.findById(dto.filmId())
                    .orElseThrow(() -> new ResourceNotFoundException("Film registry not found with ID: " + dto.filmId())));
        persistedPrice.setPrice(dto.price());
        persistedPrice.setStartValidity(dto.startValidity());
        persistedPrice.setEndValidity(dto.endValidity());
        
        Price updatedPrice = repository.save(persistedPrice);
        
        return new PriceDTO(
                updatedPrice.getId(),
                updatedPrice.getFilm().getId(),
                updatedPrice.getPrice(),
                updatedPrice.getStartValidity(),
                updatedPrice.getEndValidity()
                );
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one historical price registry!");
        
        Price price = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(price);
    }

}
