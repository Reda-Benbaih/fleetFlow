package org.example.fleetflow.DTO.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.entities.VehicleStatus;

@Getter
@Setter
public class VehiclePostDTO {
    @NotBlank(message = "numero obligatoire")
    private String registrationNumber;

    @NotBlank(message = "le type est obligatoire")
    private String type;

    @NotBlank(message = "la capacity est obligatoire")
    @Positive(message = "la capacité est obligatoire")
    private Integer capacity;

    @NotBlank(message = "le status est obligatoire")
    private VehicleStatus vehicleStatus;
}
