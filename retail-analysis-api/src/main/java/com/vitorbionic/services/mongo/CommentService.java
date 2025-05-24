package com.vitorbionic.services.mongo;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.exceptions.RequiredObjectIsNullException;
import com.vitorbionic.exceptions.ResourceNotFoundException;
import com.vitorbionic.model.mongo.Comment;
import com.vitorbionic.repository.mongo.CommentRepository;
import com.vitorbionic.repository.postgres.warehouse.ClientRepository;
import com.vitorbionic.repository.postgres.warehouse.FilmRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;
    
    @Autowired
    private FilmRepository filmRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    private static final Logger logger = Logger.getLogger(CommentService.class.getName());
    
    public List<Comment> findAll() {
        
        logger.info("Finding all comments!");
        
        return repository.findAll();
    }
    
    public Comment findById(String id) {
        
        logger.info("Finding one comment!");
        
        Comment comment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        return comment;
    }
    
    public Comment create(Comment comment) {
        if (comment == null)
            throw new RequiredObjectIsNullException();
        comment.setId(null);
        
        logger.info("Creating one comment!");
        
        filmRepository.findById(comment.getFilmId())
            .orElseThrow(() -> new ResourceNotFoundException("Film not found with ID: " + comment.getFilmId()));
        clientRepository.findById(comment.getClientId())
            .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + comment.getClientId()));
        
        comment.setCommentInstant(Instant.now());
        
        return repository.save(comment);
    }
    
    public Comment update(Comment comment) {
        if (comment == null)
            throw new RequiredObjectIsNullException();
        
        logger.info("Updating one comment!");    
        
        Comment persistedComment = repository.findById(comment.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        filmRepository.findById(comment.getFilmId())
            .orElseThrow(() -> new ResourceNotFoundException("Film not found with ID: " + comment.getFilmId()));
        clientRepository.findById(comment.getClientId())
            .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + comment.getClientId()));
        
        persistedComment.setFilmId(comment.getFilmId());
        persistedComment.setClientId(comment.getClientId());
        persistedComment.setComment(comment.getComment());
        persistedComment.setEvaluation(comment.getEvaluation());
        persistedComment.setCommentInstant(Instant.now());
        
        return repository.save(persistedComment);
    }
    
    public void delete(String id) {
        
        logger.info("Deleting one comment!");
        
        Comment comment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        
        repository.delete(comment);
    }
}
