package org.example.fleetflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "client")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String name;
    private String email;
    private String city;
    private Long number;

    @OneToMany(mappedBy = "client",cascade = CascadeType.PERSIST)
    private List<Delivery> deliveries;
}
