package org.example.fleetflow.repositories;

import org.example.fleetflow.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    boolean existsByEmail(String email);
}
