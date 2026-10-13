package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Agence;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {
}