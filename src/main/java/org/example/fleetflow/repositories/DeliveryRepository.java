package org.example.fleetflow.repositories;

import org.example.fleetflow.entities.Delivery;
import org.example.fleetflow.entities.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {

  List<Delivery> finByStatus(DeliveryStatus status);
  List<Delivery> findByClientId(Integer id);


  @Query("SELECT d FROM Delivery d " +
          "WHERE d.deliveryDate BETWEEN :beguinDate AND :EndDate")
  List<Delivery> findDeliveryEntreDeuxDates(@Param("Beguindate") LocalDate dateDebut,
                                               @Param("EndDate") LocalDate dateFin);

 @Query(" SELECT d FROM Delivery d" +
         " WHERE d.endAddress=:city")
 List<Delivery> findByEndAddress(@Param("city") String city);
}
