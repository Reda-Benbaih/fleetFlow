package org.example.fleetflow.mapper;

import org.example.fleetflow.DTO.vehicle.VehicleGetDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePostDTO;
import org.example.fleetflow.DTO.vehicle.VehiclePutDTO;
import org.example.fleetflow.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    //Get
    VehicleGetDTO toGetDTO(Vehicle vehicle);
    //Post
    Vehicle toEntity(VehiclePostDTO vehiclePostDTO);
    //Put
    void updateEntityFromPutDTO(VehiclePutDTO vehiclePutDTO , @MappingTarget Vehicle vehicle);
}
