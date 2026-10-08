package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Equipement;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iEquipementRepository extends JpaRepository<Equipement, Long> {
}