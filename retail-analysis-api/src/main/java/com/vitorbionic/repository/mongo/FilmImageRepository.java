package com.vitorbionic.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.mongo.FilmImage;

@Repository
public interface FilmImageRepository extends MongoRepository<FilmImage, String> {

}
