package com.vitorbionic.services.postgres.temporal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.data.dto.StockDTO;
import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.temporal.Stock;
import com.vitorbionic.repository.postgres.temporal.StockRepository;
import com.vitorbionic.repository.postgres.warehouse.FilmRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private FilmRepository filmRepository;

    private static final Logger logger = Logger.getLogger(StockService.class.getName());

    public List<StockDTO> findAll() {

        logger.info("Finding all historical stock registries!");

        List<Stock> entities = repository.findAll();

        List<StockDTO> dtos = new ArrayList<>();

        for (Stock entity : entities) {
            dtos.add(new StockDTO(entity.getId(), entity.getFilm().getId(), entity.getQuantity(),
                    entity.getStartValidity(), entity.getEndValidity()));
        }

        return dtos;
    }

    public StockDTO findById(Long id) {

        logger.info("Finding one historical stock registry!");

        Stock entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return new StockDTO(entity.getId(), entity.getFilm().getId(), entity.getQuantity(), entity.getStartValidity(),
                entity.getEndValidity());
    }

    public StockDTO create(StockDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one historical stock registry!");

        Stock entity = repository.save(new Stock(null,
                filmRepository.findById(dto.filmId()).orElseThrow(
                        () -> new ResourceNotFoundException("Film registry not found with ID: " + dto.filmId())),
                dto.quantity(), dto.startValidity(), dto.endValidity()));

        return new StockDTO(entity.getId(), entity.getFilm().getId(), entity.getQuantity(), entity.getStartValidity(),
                entity.getEndValidity());
    }

    public StockDTO update(StockDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating one historical stock registry!");

        Stock persistedStock = repository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        persistedStock.setFilm(filmRepository.findById(dto.filmId())
                .orElseThrow(() -> new ResourceNotFoundException("Film registry not found with ID: " + dto.filmId())));
        persistedStock.setQuantity(dto.quantity());
        persistedStock.setStartValidity(dto.startValidity());
        persistedStock.setEndValidity(dto.endValidity());

        Stock updatedStock = repository.save(persistedStock);

        return new StockDTO(updatedStock.getId(), updatedStock.getFilm().getId(), updatedStock.getQuantity(),
                updatedStock.getStartValidity(), updatedStock.getEndValidity());
    }

    public void delete(Long id) {

        logger.info("Deleting one historical stock registry!");

        Stock stock = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(stock);
    }
}
