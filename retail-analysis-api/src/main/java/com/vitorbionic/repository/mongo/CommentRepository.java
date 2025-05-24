package com.vitorbionic.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.mongo.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
}
