package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Client;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iClientRepository extends JpaRepository<Client, Long> {
}