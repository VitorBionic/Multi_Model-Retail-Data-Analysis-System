package com.vitorbionic.model.postgres.warehouse;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fact_rental")
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time dimTime;
    
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film dimFilm;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client dimClient;
    
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store dimStore;
    
    private Double paidValue;
    private Integer quantity;
    
    public Rental() {}

    public Rental(Long id, Time dimTime, Film dimFilm, Client dimClient, Store dimStore, Double paidValue,
            Integer quantity) {
        this.id = id;
        this.dimTime = dimTime;
        this.dimFilm = dimFilm;
        this.dimClient = dimClient;
        this.dimStore = dimStore;
        this.paidValue = paidValue;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getDimTime() {
        return dimTime;
    }

    public void setDimTime(Time dimTime) {
        this.dimTime = dimTime;
    }

    public Film getDimFilm() {
        return dimFilm;
    }

    public void setDimFilm(Film dimFilm) {
        this.dimFilm = dimFilm;
    }

    public Client getDimClient() {
        return dimClient;
    }

    public void setDimClient(Client dimClient) {
        this.dimClient = dimClient;
    }

    public Store getDimStore() {
        return dimStore;
    }

    public void setDimStore(Store dimStore) {
        this.dimStore = dimStore;
    }

    public Double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(Double paidValue) {
        this.paidValue = paidValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimClient, dimFilm, dimStore, dimTime, id, paidValue, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rental other = (Rental) obj;
        return Objects.equals(dimClient, other.dimClient) && Objects.equals(dimFilm, other.dimFilm)
                && Objects.equals(dimStore, other.dimStore) && Objects.equals(dimTime, other.dimTime)
                && Objects.equals(id, other.id) && Objects.equals(paidValue, other.paidValue)
                && Objects.equals(quantity, other.quantity);
    }
    
}
