package com.vitorbionic.repository.postgres.warehouse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.warehouse.Rental;
import com.vitorbionic.projections.FilmAggregation;
import com.vitorbionic.projections.GenreAggregation;
import com.vitorbionic.projections.MonthAggregation;
import com.vitorbionic.projections.YearAggregation;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(value = """
            SELECT f.genre AS genre, SUM(r.paidValue) AS total
            FROM fact_rental r
            JOIN dim_film f ON r.film_id = f.id
            GROUP BY f.genre
            """, nativeQuery = true)
    List<GenreAggregation> rollupByGenre();

    @Query(value = """
        SELECT f.genre AS genre, f.title AS title, SUM(r.paidValue) AS total
        FROM fact_rental r
        JOIN dim_film f ON r.film_id = f.id
        GROUP BY f.genre, f.title
        """, nativeQuery = true)
    List<FilmAggregation> drilldownToFilm();

    @Query(value = """
        SELECT t.year AS year, SUM(r.paidValue) AS total
        FROM fact_rental r
        JOIN dim_time t ON r.time_id = t.id
        GROUP BY t.year
        """, nativeQuery = true)
    List<YearAggregation> rollupByYear();

    @Query(value = """
        SELECT t.year AS year, t.month AS month, SUM(r.paidValue) AS total
        FROM fact_rental r
        JOIN dim_time t ON r.time_id = t.id
        GROUP BY t.year, t.month
        ORDER BY t.year, t.month
        """, nativeQuery = true)
    List<MonthAggregation> drilldownToMonth();
}
