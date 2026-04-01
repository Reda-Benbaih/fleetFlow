package org.example.fleetflow.DTO.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverPostDTO {
    private String name;
    private Long number;
    private String licenceType;
    private Boolean available;
}
