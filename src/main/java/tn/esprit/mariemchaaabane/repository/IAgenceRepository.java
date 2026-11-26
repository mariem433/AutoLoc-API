package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemchaaabane.domain.Agence;

import java.util.List;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {

    Agence findByNom(String nom);
    List<Agence> findByVilleContainingIgnoreCaseAndAdresse(String ville, String adresse);
}