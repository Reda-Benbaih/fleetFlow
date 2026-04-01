package org.example.fleetflow.DTO.vehicle;

import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.entities.VehicleStatus;

@Getter
@Setter
public class VehiclePostDTO {
    private String registrationNumber;
    private String type;
    private Integer capacity;
    private VehicleStatus vehicleStatus;
}
