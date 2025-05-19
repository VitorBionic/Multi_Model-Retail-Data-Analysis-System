package com.vitorbionic.repository.postgres.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.warehouse.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {}
