package org.example.fleetflow.repositories;

import org.example.fleetflow.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
