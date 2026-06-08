package org.example.fleetflow.DTO.driver;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverPutDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotNull(message = "Le téléphone est obligatoire")
    private Long number;

    @NotBlank(message = "Le type de permis est obligatoire")
    private String licenceType;

    @NotNull(message = "La disponibilité est obligatoire")
    private Boolean available;

    @NotBlank(message = "L'email est obligatoire") // <-- ADDED THIS
    @Email(message = "Format d'email invalide")    // <-- ADDED THIS
    private String email;
}
