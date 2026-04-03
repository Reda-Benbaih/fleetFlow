package org.example.fleetflow.Controller;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.vehicle.VehicleGetDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePostDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePutDTO;
import org.example.fleetflow.entities.VehicleStatus;
import org.example.fleetflow.Service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleGetDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicle());
    }

    @PostMapping
    public ResponseEntity<VehicleGetDTO> createVehicle(@RequestBody VehiclePostDTO postDTO) {
        return new ResponseEntity<>(vehicleService.saveVehicle(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleGetDTO> updateVehicle(@PathVariable Integer id, @RequestBody VehiclePutDTO putDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, putDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleGetDTO>> getVehiclesByStatus(@PathVariable VehicleStatus status) {
        return ResponseEntity.ok(vehicleService.getVehicleByStatus(status));
    }

    @GetMapping("/capacity/{minCapacity}")
    public ResponseEntity<List<VehicleGetDTO>> getVehiclesByCapacity(@PathVariable Integer minCapacity) {
        return ResponseEntity.ok(vehicleService.getVehicleGreatThan(minCapacity));
    }
}