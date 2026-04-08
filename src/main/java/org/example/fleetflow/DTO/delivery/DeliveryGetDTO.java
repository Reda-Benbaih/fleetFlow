package org.example.fleetflow.DTO.delivery;

import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow.entities.DeliveryStatus;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryGetDTO {
    private Integer id;
    private LocalDate deliveryDate;
    private String startAddress;
    private String endAddress;
    private DeliveryStatus deliveryStatus;

}
