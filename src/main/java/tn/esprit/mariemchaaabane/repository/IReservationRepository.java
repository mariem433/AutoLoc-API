package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Client;
import tn.esprit.mariemchaaabane.domain.Reservation;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClientEmail(String email);
}
