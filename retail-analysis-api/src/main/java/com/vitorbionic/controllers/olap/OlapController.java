package com.vitorbionic.controllers.olap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorbionic.projections.FilmAggregation;
import com.vitorbionic.projections.GenreAggregation;
import com.vitorbionic.projections.MonthAggregation;
import com.vitorbionic.projections.YearAggregation;
import com.vitorbionic.services.olap.OlapService;

@RestController
@RequestMapping(value = "/api/olap")
public class OlapController {
    
    @Autowired
    private OlapService service;
    
    @GetMapping(value = "/rollupByGenre")
    public List<GenreAggregation> rollupByGenre() {
        return service.rollupByGenre();
    }
    
    @GetMapping(value = "/drilldownToFilm")
    public List<FilmAggregation> drilldownToFilm() {
        return service.drilldownToFilm();
    }
    
    @GetMapping(value = "/rollupByYear")
    public List<YearAggregation> rollupByYear() {
        return service.rollupByYear();
    }
    
    @GetMapping(value = "/drilldownToMonth")
    public List<MonthAggregation> drilldownToMonth() {
        return service.drilldownToMonth();
    }
}
