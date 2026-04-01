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
    private Integer id;

    private String name;
    private Long number;
    private String licenceType;
    private Boolean available;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.PERSIST)
    private List<Delivery> deliveries;
}
