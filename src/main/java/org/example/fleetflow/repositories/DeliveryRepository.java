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

  List<Delivery> findByDeliveryStatus(DeliveryStatus status);
  List<Delivery> findByClientId(Integer id);


  @Query("SELECT d FROM Delivery d " +
          "WHERE d.deliveryDate BETWEEN :BeginDate AND :EndDate")
  List<Delivery> findDeliveryEntreDeuxDates(@Param("BeginDate") LocalDate dateDebut,
                                               @Param("EndDate") LocalDate dateFin);

 @Query(" SELECT d FROM Delivery d" +
         " WHERE d.endAddress=:city")
 List<Delivery> findByEndAddress(@Param("city") String city);

// List<Delivery> findByStartAddress(String startAddress);

// List<Delivery> findByEndAddressContaining(String endAddress);

// List<Delivery> findAllByOrderByDeliveryDateDesc();

// List<Delivery> findByClientIdAndDeliveryStatus(int clientId ,DeliveryStatus deliveryStatus);

// @Query(value = "SELECT de FROM delivery de JOIN driver dr on de.driver_id = dr.id WHERE dr.available = true ",nativeQuery = true)
//    List<Delivery> findDeleveriesWhereDriverAvailable();
//
// @Query(value = "select de from delivery de join vehicle ve on de.vehicle_id = ve.id where ve.vehicle_status = AVAILABLE " ,nativeQuery = true)
//    List<Delivery> findDeliveriesWhereVehicleAvailable();
//
// @Query(value = "select de from delivery de join client cl on de.client_id = cl.id where cl.city = :city",nativeQuery = true)
//    List<Delivery> findDeliveriesByCLientCity(@Param("city")String city);
//
// @Query(value = "select de from delivery de where delivery_date > CURRENT_DATE ",nativeQuery = true)
//    List<Delivery> findDeliveriesHasDateMoreThanNow();
//
// @Query(value = "select delivery_status ,count(*) from delivery group by deliviry_status")
//    List<Object> countAllDeliveryStatus();
//
// @Query(value = "select end_address , count(*) from delivery group by end_address",nativeQuery = true)
//    List<Object> countAllDeliveriesEndAddress();
//
// @Query(value = "SELECT driver_id, COUNT(*) as total FROM delivery GROUP BY driver_id ORDER BY total DESC", nativeQuery = true)
//    List<Object[]> topDrivers();
//
// @Query(value = "SELECT vehicle_id, COUNT(*) as total FROM delivery GROUP BY vehicle_id ORDER BY total DESC", nativeQuery = true)
//    List<Object[]> topVehicles();
}
