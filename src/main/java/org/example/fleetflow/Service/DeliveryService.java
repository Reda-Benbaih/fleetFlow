package org.example.fleetflow.Service;

import org.example.fleetflow.DTO.delivery.DeliveryGetDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPostDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPutDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryService {
    DeliveryGetDTO ajouterDelivery(DeliveryPostDTO deliveryPostDTO);
    Page<DeliveryGetDTO> getAllDeliveries(int page , int size, String sort, String type);
    DeliveryGetDTO getDeliveryById(Integer id);
    DeliveryGetDTO updateDelivery(Integer id, DeliveryPutDTO deliveryPutDTO);
    void deleteDelivery(Integer id);
    List<DeliveryGetDTO> getDeliveriesEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin);
    List<DeliveryGetDTO> getDeliveriesByCity(String city);
}
