package org.example.fleetflow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DriverDTO {
    private String name;
    private Long number;
    private Boolean available;
}
