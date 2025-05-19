package com.vitorbionic.services.postgres.warehouse;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.postgres.warehouse.Time;
import com.vitorbionic.repository.postgres.warehouse.TimeRepository;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;
    
    private Logger logger = Logger.getLogger(TimeService.class.getName());
    
    public List<Time> findAll() {
        
        logger.info("Finding all time dimension registries!");
        
        return repository.findAll();
    }
    
    public Time findById(Long id) {
        
        logger.info("Finding one time dimension registry!");
        
        Time time = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return time;
    }
    
    public Time create(Time time) {
        if (time == null)
            throw new RequiredObjectIsNullException();
        time.setId(null);
        
        logger.info("Creating one time dimension registry!");        
        
        return repository.save(time);
    }
    
    public Time update(Time time) {
        if (time == null)
            throw new RequiredObjectIsNullException();
        
        logger.info("Updating one time dimension registry!");    
        
        Time persistedTime = repository.findById(time.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        persistedTime.setYear(time.getYear());
        persistedTime.setMonth(time.getMonth());
        persistedTime.setDay(time.getDay());
        persistedTime.setDate(time.getDate());
        
        return repository.save(persistedTime);
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one time dimension registry!");
        
        Time time = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(time);
    }
    
}
