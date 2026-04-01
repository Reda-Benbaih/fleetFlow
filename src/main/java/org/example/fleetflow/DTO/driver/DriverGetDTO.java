package org.example.fleetflow.DTO.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverGetDTO {
    private Integer id;
    private String name;
    private Long number;
    private Boolean available;
}
