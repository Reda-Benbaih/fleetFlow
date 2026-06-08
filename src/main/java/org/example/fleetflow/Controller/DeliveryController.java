package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.delivery.DeliveryGetDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPostDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPutDTO;
import org.example.fleetflow.Service.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@AllArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeliveryGetDTO> ajouterDelivery(@Valid @RequestBody DeliveryPostDTO deliveryPostDTO) {
        DeliveryGetDTO createdDelivery = deliveryService.ajouterDelivery(deliveryPostDTO);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'MANAGER')")
    public ResponseEntity<Page<DeliveryGetDTO>> getAllDeliveries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ) {
        Page<DeliveryGetDTO> deliveries = deliveryService.getAllDeliveries(page, size, sort, type);
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'MANAGER')")
    public ResponseEntity<DeliveryGetDTO> getDeliveryById(@PathVariable Integer id) {
        DeliveryGetDTO delivery = deliveryService.getDeliveryById(id);
        return ResponseEntity.ok(delivery);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    public ResponseEntity<DeliveryGetDTO> updateDelivery(@PathVariable Integer id,
                                                         @Valid @RequestBody DeliveryPutDTO deliveryPutDTO) {
        DeliveryGetDTO updatedDelivery = deliveryService.updateDelivery(id, deliveryPutDTO);
        return ResponseEntity.ok(updatedDelivery);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Integer id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/between-dates")
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'MANAGER')")
    public ResponseEntity<List<DeliveryGetDTO>> getDeliveriesEntreDeuxDates(@RequestParam LocalDate dateDebut,
                                                                            @RequestParam LocalDate dateFin) {
        List<DeliveryGetDTO> deliveries = deliveryService.getDeliveriesEntreDeuxDates(dateDebut, dateFin);
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/city")
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'MANAGER')")
    public ResponseEntity<List<DeliveryGetDTO>> getDeliveriesByCity(@RequestParam String city) {
        List<DeliveryGetDTO> deliveries = deliveryService.getDeliveriesByCity(city);
        return ResponseEntity.ok(deliveries);
    }
}