package com.vitorbionic.model.postgres.temporal;

import java.time.LocalDate;
import java.util.Objects;

import com.vitorbionic.model.postgres.warehouse.Film;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "price_temporal")
public class Price {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    
    private Double price;
    
    @Column(name = "start_validity")
    private LocalDate startValidity;
    
    @Column(name = "end_validity")
    private LocalDate endValidity;

    public Price() {}

    public Price(Long id, Film film, Double price, LocalDate startValidity, LocalDate endValidity) {
        this.id = id;
        this.film = film;
        this.price = price;
        this.startValidity = startValidity;
        this.endValidity = endValidity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getStartValidity() {
        return startValidity;
    }

    public void setStartValidity(LocalDate startValidity) {
        this.startValidity = startValidity;
    }

    public LocalDate getEndValidity() {
        return endValidity;
    }

    public void setEndValidity(LocalDate endValidity) {
        this.endValidity = endValidity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endValidity, film, id, price, startValidity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Price other = (Price) obj;
        return Objects.equals(endValidity, other.endValidity) && Objects.equals(film, other.film)
                && Objects.equals(id, other.id) && Objects.equals(price, other.price)
                && Objects.equals(startValidity, other.startValidity);
    }
    
}
