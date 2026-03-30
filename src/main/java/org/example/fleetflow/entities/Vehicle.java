package org.example.fleetflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String registrationNumber;
    private String type;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    @OneToMany(mappedBy = "delivery",cascade = CascadeType.ALL)
    private List<Delivery> deliveries;
}
