package org.example.fleetflow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class DeliveryDTO {
    private String deliveryDate;
    private LocalDate startAddress;
    private LocalDate endAddress;

}
