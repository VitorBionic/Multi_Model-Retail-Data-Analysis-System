package com.vitorbionic.repository.postgres.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.warehouse.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {}
