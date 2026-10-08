package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Employe;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iEmployeRepository extends JpaRepository<Employe, Long> {
}