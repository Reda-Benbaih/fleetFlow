package org.example.fleetflow.DTO.delivery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.entities.DeliveryStatus;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryPostDTO {
    @NotNull(message = "La date de livraison est obligatoire")
    private LocalDate deliveryDate;

    @NotBlank(message = "L'adresse de départ est obligatoire")
    private String startAddress;

    @NotBlank(message = "L'adresse de destination est obligatoire")
    private String endAddress;

    @NotNull(message = "Le statut est obligatoire")
    private DeliveryStatus deliveryStatus;

    @NotNull(message = "Le clientId est obligatoire")
    private Integer clientId;

    @NotNull(message = "Le driverId est obligatoire")
    private Integer driverId;

    @NotNull(message = "Le vehicleId est obligatoire")
    private Integer vehicleId;
}
