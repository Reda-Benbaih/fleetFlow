package org.example.fleetflow.DTO.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientGetDTO {
    private Integer id;
    private String name;
    private String email;
    private Long number;
}
