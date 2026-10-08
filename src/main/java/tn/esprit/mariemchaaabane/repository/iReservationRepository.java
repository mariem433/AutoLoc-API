package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Reservation;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iReservationRepository extends JpaRepository<Reservation, Long> {
}
