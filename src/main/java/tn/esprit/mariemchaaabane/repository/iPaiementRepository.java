package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Paiement;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iPaiementRepository extends JpaRepository<Paiement, Long> {
}