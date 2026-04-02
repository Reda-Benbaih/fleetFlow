package org.example.fleetflow.repositories;

import org.example.fleetflow.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
