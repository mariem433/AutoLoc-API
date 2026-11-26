package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.mariemchaaabane.domain.Client;

import java.time.LocalDate;
import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Long> {
    @Query("select client from Client client " +
            "join client.reservations res " +
            "where res.dateDebut between :debut and :fin ")
    List<Client> findByReservations(LocalDate debut , LocalDate fin );
}