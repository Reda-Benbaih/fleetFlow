package org.example.fleetflow.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.vehicle.VehicleGetDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePostDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePutDTO;
import org.example.fleetflow.entities.Vehicle;
import org.example.fleetflow.entities.VehicleStatus;
import org.example.fleetflow.mapper.VehicleMapper;
import org.example.fleetflow.repositories.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public VehicleGetDTO saveVehicle(VehiclePostDTO vehiclePostDTO){
        Vehicle vehicle = vehicleMapper.toEntity(vehiclePostDTO);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toGetDTO(savedVehicle);
    }

    @Transactional
    public Page<VehicleGetDTO> getAllVehicle(Pageable pageable){
        return vehicleRepository.findAll(pageable)
                .map(vehicle -> vehicleMapper.toGetDTO(vehicle));
    }

    public VehicleGetDTO updateVehicle(Integer id, VehiclePutDTO vehiclePutDTO){
        Vehicle existVehicle = vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("Vehicle does not exist"));

        vehicleMapper.updateEntityFromPutDTO(vehiclePutDTO , existVehicle);

        Vehicle savedVehicle = vehicleRepository.save(existVehicle);

        return vehicleMapper.toGetDTO(savedVehicle);
    }

    @Transactional
    public void deleteVehicle(Integer id){
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public List<VehicleGetDTO> getVehicleByStatus(VehicleStatus vehicleStatus){
        return vehicleRepository.findByVehicleStatus(vehicleStatus).stream()
                .map(vehicle -> vehicleMapper.toGetDTO(vehicle))
                .toList();
    }

    @Transactional
    public List<VehicleGetDTO> getVehicleGreatThan(Integer number){
        return vehicleRepository.findByCapacityGreaterThan(number).stream()
                .map(vehicle -> vehicleMapper.toGetDTO(vehicle))
                .toList();
    }

}
