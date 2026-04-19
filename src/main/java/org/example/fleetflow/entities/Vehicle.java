package org.example.fleetflow.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String registrationNumber;
    private String type;
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    @OneToMany(mappedBy = "vehicle",cascade = CascadeType.PERSIST)
    private List<Delivery> deliveries;
}
