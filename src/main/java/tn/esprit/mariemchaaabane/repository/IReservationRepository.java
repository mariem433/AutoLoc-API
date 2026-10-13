package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
