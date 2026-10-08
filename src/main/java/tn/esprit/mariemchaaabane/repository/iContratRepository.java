package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iContratRepository extends JpaRepository<tn.esprit.mariemchaaabane.domain.Contrat, Long> {
}