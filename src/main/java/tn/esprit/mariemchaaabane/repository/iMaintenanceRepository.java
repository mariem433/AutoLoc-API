package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Maintenance;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
