package org.example.fleetflow.Service;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.delivery.DeliveryGetDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPostDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPutDTO;
import org.example.fleetflow.entities.Client;
import org.example.fleetflow.entities.Delivery;
import org.example.fleetflow.entities.Driver;
import org.example.fleetflow.entities.Vehicle;
import org.example.fleetflow.mapper.DeliveryMapper;
import org.example.fleetflow.repositories.ClientRepository;
import org.example.fleetflow.repositories.DeliveryRepository;
import org.example.fleetflow.repositories.DriverRepository;
import org.example.fleetflow.repositories.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final ClientRepository clientRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public DeliveryGetDTO ajouterDelivery(DeliveryPostDTO deliveryPostDTO) {
        Delivery delivery = deliveryMapper.toEntity(deliveryPostDTO);

        Client client = clientRepository.findById(deliveryPostDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + deliveryPostDTO.getClientId()));

        Driver driver = driverRepository.findById(deliveryPostDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver introuvable avec l'id : " + deliveryPostDTO.getDriverId()));

        Vehicle vehicle = vehicleRepository.findById(deliveryPostDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle introuvable avec l'id : " + deliveryPostDTO.getVehicleId()));

        delivery.setClient(client);
        delivery.setDriver(driver);
        delivery.setVehicle(vehicle);

        Delivery savedDelivery = deliveryRepository.save(delivery);
        return deliveryMapper.toGetDTO(savedDelivery);
    }

    public Page<DeliveryGetDTO> getAllDeliveries(int page , int size,String sort,String type) {

        Sort sortby = type.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();

        Pageable pageable = PageRequest.of(page,size,sortby);

        return deliveryRepository.findAll(pageable)
                .map(deliveryMapper::toGetDTO);
    }

    public DeliveryGetDTO getDeliveryById(Integer id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison introuvable avec l'id : " + id));
        return deliveryMapper.toGetDTO(delivery);
    }

    public DeliveryGetDTO updateDelivery(Integer id, DeliveryPutDTO deliveryPutDTO) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison introuvable avec l'id : " + id));

        deliveryMapper.updateEntityFromPutDTO(deliveryPutDTO, delivery);

        Client client = clientRepository.findById(deliveryPutDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + deliveryPutDTO.getClientId()));

        Driver driver = driverRepository.findById(deliveryPutDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver introuvable avec l'id : " + deliveryPutDTO.getDriverId()));

        Vehicle vehicle = vehicleRepository.findById(deliveryPutDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle introuvable avec l'id : " + deliveryPutDTO.getVehicleId()));

        delivery.setClient(client);
        delivery.setDriver(driver);
        delivery.setVehicle(vehicle);

        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return deliveryMapper.toGetDTO(updatedDelivery);
    }

    public void deleteDelivery(Integer id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison introuvable avec l'id : " + id));

        deliveryRepository.delete(delivery);
    }

    public List<DeliveryGetDTO> getDeliveriesEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin) {
        return deliveryRepository.findDeliveryEntreDeuxDates(dateDebut, dateFin)
                .stream()
                .map(deliveryMapper::toGetDTO)
                .toList();
    }

    public List<DeliveryGetDTO> getDeliveriesByCity(String city) {
        return deliveryRepository.findByEndAddress(city)
                .stream()
                .map(deliveryMapper::toGetDTO)
                .toList();
    }
}