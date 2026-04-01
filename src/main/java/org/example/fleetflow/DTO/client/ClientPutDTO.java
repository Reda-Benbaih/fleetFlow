package org.example.fleetflow.DTO.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientPutDTO {
    private String name;
    private String email;
    private String city;
    private Long number;
}
