package com.vitorbionic.controllers.crud.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorbionic.model.mongo.Comment;
import com.vitorbionic.services.mongo.CommentService;

@RestController
@RequestMapping(value = "/api/mongo/comment")
public class CommentController {
    
    @Autowired
    private CommentService service;
    
    @GetMapping
    public List<Comment> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Comment findById(@PathVariable String id) {
        return service.findById(id);
    }
    
    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        return service.create(comment);
    }
    
    @PutMapping
    public Comment update(@RequestBody Comment comment) {
        return service.update(comment);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
