package com.goit.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fromPlanetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "fromPlanetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketsTo = new ArrayList<>();

    public Planet() {
    }

    @Override
    public String toString() {
        return "Planet(id=" + this.getId() + ", name=" + this.getName() + ")\n";
    }
}
