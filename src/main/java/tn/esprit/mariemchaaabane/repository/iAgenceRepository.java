package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Agence;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iAgenceRepository extends JpaRepository<Agence, Long> {
}