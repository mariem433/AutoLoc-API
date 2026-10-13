package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Employe;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {
}