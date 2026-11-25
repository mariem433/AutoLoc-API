package tn.esprit.mariemchaaabane.service;

import org.springframework.stereotype.Service;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeDTO;

import java.util.List;



public interface IVehiculeService {

    Vehicule create(Vehicule behicule);
    Vehicule findById(Long id);
    List<VehiculeDTO> findAll();
    VehiculeDTO toDTO (Vehicule vehicule);
    void deleteById(Long id);
    Vehicule update(Vehicule vehicule);
    Vehicule assignAgence(Long idVehicule, Long idAgence);
    Vehicule assignEquipement(Long idVehicule, Long idEquipement);
    Vehicule unassignEquipement(Long idVehicule, Long idEquipement);
    Vehicule addAndAssignAgence(Vehicule vehicule, Long idAgence);
}
