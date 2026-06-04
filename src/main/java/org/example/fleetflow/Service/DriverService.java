package org.example.fleetflow.Service;

import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.DTO.driver.DriverPostDTO;
import org.example.fleetflow.DTO.driver.DriverPutDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DriverService {
    void deleteDriver(Integer id);
    List<DriverGetDTO> getAllDriversAvailable();
    DriverGetDTO updateDriver(Integer id, DriverPutDTO driverPutDTO);
    Page<DriverGetDTO> getAllDrivers(int page , int size, String sort, String type);
    DriverGetDTO saveDriver(DriverPostDTO postDTO);
}
