package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Equipement;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}