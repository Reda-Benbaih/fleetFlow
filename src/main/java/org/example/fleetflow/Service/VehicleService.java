package org.example.fleetflow.Service;

import org.example.fleetflow.DTO.vehicle.VehicleGetDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePostDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePutDTO;
import org.example.fleetflow.entities.VehicleStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {
    VehicleGetDTO saveVehicle(VehiclePostDTO vehiclePostDTO);
    Page<VehicleGetDTO> getAllVehicle(int page , int size, String sort, String type);
    VehicleGetDTO updateVehicle(Integer id, VehiclePutDTO vehiclePutDTO);
    void deleteVehicle(Integer id);
    List<VehicleGetDTO> getVehicleByStatus(VehicleStatus vehicleStatus);
    List<VehicleGetDTO> getVehicleGreatThan(Integer number);
}
