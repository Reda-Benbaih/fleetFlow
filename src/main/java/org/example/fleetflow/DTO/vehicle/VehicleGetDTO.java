package org.example.fleetflow.DTO.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleGetDTO {
    private Integer id;
    private String registrationNumber;
    private String type;
    private Integer capacity;
}
