package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.DTO.driver.DriverPostDTO;
import org.example.fleetflow.DTO.driver.DriverPutDTO;
import org.example.fleetflow.Service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Page<DriverGetDTO>> getAllDrivers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ){
        return ResponseEntity.ok(driverService.getAllDrivers(page, size, sort, type));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DriverGetDTO> createDriver(@Valid @RequestBody DriverPostDTO postDTO) {
        DriverGetDTO savedDriver = driverService.saveDriver(postDTO);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DriverGetDTO> updateDriver(@PathVariable Integer id,@Valid @RequestBody DriverPutDTO putDTO) {
        return ResponseEntity.ok(driverService.updateDriver(id, putDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<DriverGetDTO>> getAvailableDrivers() {
        List<DriverGetDTO> availableDrivers = driverService.getAllDriversAvailable();
        return ResponseEntity.ok(availableDrivers);
    }
}