package tn.esprit.mariemchaaabane.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;
}