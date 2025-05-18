package com.vitorbionic.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.warehouse.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {}
