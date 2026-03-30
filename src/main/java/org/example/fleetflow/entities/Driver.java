package org.example.fleetflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "driver")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private long number;
    private String licenceType;
    private boolean available;

    @OneToMany(mappedBy = "delivery",cascade = CascadeType.ALL)
    private List<Delivery> deliveries;
}
