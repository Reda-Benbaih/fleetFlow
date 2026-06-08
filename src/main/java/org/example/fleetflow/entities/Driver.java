package org.example.fleetflow.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "driver")
@PrimaryKeyJoinColumn(name = "id") // Links this table's ID to the User table's ID
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@SuperBuilder // Changed from @Builder
public class Driver extends User {

    private String name;
    private Long number;
    private String licenceType;
    private Boolean available;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.PERSIST)
    private List<Delivery> deliveries;
}