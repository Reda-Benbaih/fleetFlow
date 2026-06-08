package org.example.fleetflow.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@SuperBuilder // Changed from @Builder
public class Client extends User {

    private String name;
    private String city;
    private Long number;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Delivery> deliveries;
}