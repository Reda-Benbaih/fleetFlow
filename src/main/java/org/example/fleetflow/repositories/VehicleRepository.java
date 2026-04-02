package org.example.fleetflow.repositories;


import org.example.fleetflow.entities.Vehicle;
import org.example.fleetflow.entities.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    List<Vehicle> findByVehicleStatus(VehicleStatus status);

    List<Vehicle> findByCapacityGreaterThan(Integer capacity);
}
