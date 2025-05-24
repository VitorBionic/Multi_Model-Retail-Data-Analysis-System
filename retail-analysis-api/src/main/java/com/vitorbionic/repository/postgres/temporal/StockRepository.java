package com.vitorbionic.repository.postgres.temporal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.temporal.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
