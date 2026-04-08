package org.example.fleetflow.DTO.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientPutDTO {
   @NotBlank(message = "le nom est obligatoire")
    private String name;

   @Email(message = "mail invalid")
   @NotBlank(message = "le mail est obligatoire")
    private String email;

   @NotBlank(message = "la ville est obligatoire")
    private String city;

   @NotBlank(message = "le numero est oblegatoire")
    private Long number;
}
