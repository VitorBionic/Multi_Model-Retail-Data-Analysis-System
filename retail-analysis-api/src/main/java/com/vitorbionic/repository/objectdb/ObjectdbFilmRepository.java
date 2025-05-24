package com.vitorbionic.repository.objectdb;

import org.springframework.stereotype.Repository;

import com.vitorbionic.model.objectdb.Film;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ObjectdbFilmRepository extends JpaRepository<Film, Long> {
    
}
