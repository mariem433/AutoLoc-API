package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tn.esprit.mariemchaaabane.domain.Vehicule;

public interface iVehiculeRepository extends JpaRepository<Vehicule, Long> {

}
