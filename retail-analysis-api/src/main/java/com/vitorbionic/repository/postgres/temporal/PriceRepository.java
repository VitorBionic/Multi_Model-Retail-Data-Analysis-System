package com.vitorbionic.repository.postgres.temporal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.temporal.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
