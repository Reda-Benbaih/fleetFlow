package org.example.fleetflow.DTO.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientPostDTO {
    @NotBlank(message = "le nom est obligatoire")
    private String name;

    @Email(message = "le mail est invalid")
    @NotBlank(message = "le mail est obligatoire ")
    private String email;

    @NotBlank(message = "la ville est obligatoire ")
    private String city;

    @NotNull(message = "le numero de telephone est oblegatoire")
    private Long number;
}
