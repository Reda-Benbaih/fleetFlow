package org.example.fleetflow.repositories;

import org.example.fleetflow.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
}
