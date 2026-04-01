package org.example.fleetflow.DTO.delivery;

import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.entities.DeliveryStatus;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryPutDTO {
    private LocalDate deliveryDate;
    private String startAddress;
    private String endAddress;
    private DeliveryStatus deliveryStatus;

    private Integer clientId;
    private Integer driverId;
    private Integer vehicleId;

}
