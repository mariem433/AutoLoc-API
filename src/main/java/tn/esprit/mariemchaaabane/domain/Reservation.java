package tn.esprit.mariemchaaabane.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"client", "vehicule", "employe", "contrat"})
@EqualsAndHashCode(exclude = {"client", "vehicule", "employe", "contrat"})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutReservation statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehicule", nullable = false)
    private Vehicule vehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmploye")
    private Employe employe;

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
    private tn.esprit.mariemchaaabane.domain.Contrat contrat;
}