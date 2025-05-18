package com.vitorbionic.services.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.data.dto.RentalDTO;
import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.warehouse.Rental;
import com.vitorbionic.repository.postgres.ClientRepository;
import com.vitorbionic.repository.postgres.FilmRepository;
import com.vitorbionic.repository.postgres.RentalRepository;
import com.vitorbionic.repository.postgres.StoreRepository;
import com.vitorbionic.repository.postgres.TimeRepository;

@Service
public class RentalService {
    
    @Autowired
    private RentalRepository repository;
    
    @Autowired
    private TimeRepository timeRepository;
    
    @Autowired
    private FilmRepository filmRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private StoreRepository storeRepository;
    
    private Logger logger = Logger.getLogger(RentalService.class.getName());
    
    public List<RentalDTO> findAll() {
        
        logger.info("Finding all rentals!");
        
        List<Rental> entities = repository.findAll();
        
        List<RentalDTO> dtos = new ArrayList<>();
        for (Rental entity : entities) {
            dtos.add(new RentalDTO(
                        entity.getId(),
                        entity.getDimTime().getId(),
                        entity.getDimFilm().getId(),
                        entity.getDimClient().getId(),
                        entity.getDimStore().getId(),
                        entity.getPaidValue(),
                        entity.getQuantity())
            );
        }
        
        return dtos;
    }
    
    public RentalDTO findById(Long id) {
        
        logger.info("Finding one rental!");
        
        Rental entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return new RentalDTO(
                entity.getId(),
                entity.getDimTime().getId(),
                entity.getDimFilm().getId(),
                entity.getDimClient().getId(),
                entity.getDimStore().getId(),
                entity.getPaidValue(),
                entity.getQuantity()
                );
    }
    
    public RentalDTO create(RentalDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Creating one rental!");
        
        
        Rental entity = repository.save(new Rental(
                null,
                timeRepository.findById(dto.timeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Time registry not found with ID: " + dto.timeId())),
                filmRepository.findById(dto.filmId())
                    .orElseThrow(() -> new ResourceNotFoundException("Film registry not found with ID: " + dto.filmId())),
                clientRepository.findById(dto.clientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client registry not found with ID: " + dto.clientId())),
                storeRepository.findById(dto.storeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Store registry not found with ID: " + dto.storeId())),
                dto.paidValue(),
                dto.quantity()
                ));
        
        return new RentalDTO(
                entity.getId(),
                entity.getDimTime().getId(),
                entity.getDimFilm().getId(),
                entity.getDimClient().getId(),
                entity.getDimStore().getId(),
                entity.getPaidValue(),
                entity.getQuantity()
                );
    }
    
    public RentalDTO update(RentalDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }
        
        logger.info("Updating one rental!");
        
        Rental persistedRental = repository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedRental.setDimTime(timeRepository.findById(dto.timeId())
                .orElseThrow(() -> new ResourceNotFoundException("Time registry not found with ID: " + dto.timeId())));
        persistedRental.setDimFilm(filmRepository.findById(dto.filmId())
                .orElseThrow(() -> new ResourceNotFoundException("Film registry not found with ID: " + dto.filmId())));
        persistedRental.setDimClient(clientRepository.findById(dto.clientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client registry not found with ID: " + dto.clientId())));
        persistedRental.setDimStore(storeRepository.findById(dto.storeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Store registry not found with ID: " + dto.storeId())));
        persistedRental.setPaidValue(dto.paidValue());
        persistedRental.setQuantity(dto.quantity());
        
        Rental updatedRental = repository.save(persistedRental);
        
        return new RentalDTO(
                updatedRental.getId(),
                updatedRental.getDimTime().getId(),
                updatedRental.getDimFilm().getId(),
                updatedRental.getDimClient().getId(),
                updatedRental.getDimStore().getId(),
                updatedRental.getPaidValue(),
                updatedRental.getQuantity()
                );
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one rental!");
        
        Rental rental = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(rental); 
    }
}
