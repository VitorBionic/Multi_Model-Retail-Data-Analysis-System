package com.vitorbionic.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorbionic.model.postgres.warehouse.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
