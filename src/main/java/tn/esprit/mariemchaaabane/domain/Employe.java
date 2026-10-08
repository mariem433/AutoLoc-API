package tn.esprit.mariemchaaabane.domain;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.mariemchaaabane.domain.RoleEmploye;

@Entity
@Table(name = "employe")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "agence")
@EqualsAndHashCode(exclude = "agence")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleEmploye role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgence")
    private tn.esprit.autoloc.domain.Agence agence;
}