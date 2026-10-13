package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Paiement;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}