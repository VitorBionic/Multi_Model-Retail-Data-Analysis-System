package com.vitorbionic.services.olap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorbionic.projections.FilmAggregation;
import com.vitorbionic.projections.GenreAggregation;
import com.vitorbionic.projections.MonthAggregation;
import com.vitorbionic.projections.YearAggregation;
import com.vitorbionic.repository.postgres.warehouse.RentalRepository;

@Service
public class OlapService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<GenreAggregation> rollupByGenre() {
        return rentalRepository.rollupByGenre();
    }

    public List<FilmAggregation> drilldownToFilm() {
        return rentalRepository.drilldownToFilm();
    }

    public List<YearAggregation> rollupByYear() {
        return rentalRepository.rollupByYear();
    }

    public List<MonthAggregation> drilldownToMonth() {
        return rentalRepository.drilldownToMonth();
    }
}