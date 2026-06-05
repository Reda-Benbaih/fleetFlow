package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.vehicle.VehicleGetDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePostDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePutDTO;
import org.example.fleetflow.Service.VehicleService;
import org.example.fleetflow.entities.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Page<VehicleGetDTO>> getAllVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ) {
        return ResponseEntity.ok(vehicleService.getAllVehicle(page, size, sort, type));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<VehicleGetDTO> createVehicle(@Valid @RequestBody VehiclePostDTO postDTO) {
        return new ResponseEntity<>(vehicleService.saveVehicle(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<VehicleGetDTO> updateVehicle(@PathVariable Integer id, @Valid@RequestBody VehiclePutDTO putDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, putDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<VehicleGetDTO>> getVehiclesByStatus(@PathVariable VehicleStatus status) {
        return ResponseEntity.ok(vehicleService.getVehicleByStatus(status));
    }

    @GetMapping("/capacity/{minCapacity}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<VehicleGetDTO>> getVehiclesByCapacity(@PathVariable Integer minCapacity) {
        return ResponseEntity.ok(vehicleService.getVehicleGreatThan(minCapacity));
    }
}