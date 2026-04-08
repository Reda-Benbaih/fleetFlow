package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.DTO.driver.DriverPostDTO;
import org.example.fleetflow.DTO.driver.DriverPutDTO;
import org.example.fleetflow.Service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverGetDTO>> getAllDrivers(){
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PostMapping
    public ResponseEntity<DriverGetDTO> createDriver(@Valid @RequestBody DriverPostDTO postDTO) {
        DriverGetDTO savedDriver = driverService.saveDriver(postDTO);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverGetDTO> updateDriver(@PathVariable Integer id,@Valid @RequestBody DriverPutDTO putDTO) {
        return ResponseEntity.ok(driverService.updateDriver(id, putDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<DriverGetDTO>> getAvailableDrivers() {
        List<DriverGetDTO> availableDrivers = driverService.getAllDriversAvailable();
        return ResponseEntity.ok(availableDrivers);
    }
}
