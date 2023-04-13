package com.goit.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanetId;

    public Ticket() {
    }

    @Override
    public String toString() {
        return "Ticket(id="
                + this.getId()
                + ", createdAt="
                + this.getCreatedAt()
                + ", client="
                + this.getClient().getName()
                + ", fromPlanetId="
                + this.getFromPlanetId().getId()
                + ", toPlanetId="
                + this.getToPlanetId().getId() + ")\n";
    }
}
