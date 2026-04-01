package org.example.fleetflow.mapper;

import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.DTO.driver.DriverPostDTO;
import org.example.fleetflow.DTO.driver.DriverPutDTO;
import org.example.fleetflow.entities.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    //Get
    DriverGetDTO toGetDTO (Driver driver);
    //Post
    Driver toEntity(DriverPostDTO driverPostDTO);
    //Put
    void updateEntityFromPutDTO(DriverPutDTO driverPutDTO, @MappingTarget Driver driver);
}
