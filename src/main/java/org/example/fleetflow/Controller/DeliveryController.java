package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.delivery.DeliveryGetDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPostDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPutDTO;
import org.example.fleetflow.Service.DeliveryService;
import org.springframework.data.domain.Page;
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DeliveryGetDTO ajouterDelivery(@Valid @RequestBody DeliveryPostDTO deliveryPostDTO) {
        return deliveryService.ajouterDelivery(deliveryPostDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
    public Page<DeliveryGetDTO> getAllDeliveries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ) {
        return deliveryService.getAllDeliveries(page, size, sort, type);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DeliveryGetDTO getDeliveryById(@PathVariable Integer id) {
        return deliveryService.getDeliveryById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
    public DeliveryGetDTO updateDelivery(@PathVariable Integer id,
                                         @Valid @RequestBody DeliveryPutDTO deliveryPutDTO) {
        return deliveryService.updateDelivery(id, deliveryPutDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
    public void deleteDelivery(@PathVariable Integer id) {
        deliveryService.deleteDelivery(id);
    }

    @GetMapping("/between-dates")
    public List<DeliveryGetDTO> getDeliveriesEntreDeuxDates(@RequestParam LocalDate dateDebut,
                                                            @RequestParam LocalDate dateFin) {
        return deliveryService.getDeliveriesEntreDeuxDates(dateDebut, dateFin);
    }

    @GetMapping("/city")
    @PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
    public List<DeliveryGetDTO> getDeliveriesByCity(@RequestParam String city) {
        return deliveryService.getDeliveriesByCity(city);
    }
}