package com.vitorbionic.repository.postgres.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.warehouse.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {}
