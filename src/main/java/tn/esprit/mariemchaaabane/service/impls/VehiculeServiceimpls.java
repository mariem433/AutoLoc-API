package tn.esprit.mariemchaaabane.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mariemchaaabane.domain.Agence;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.exception.ResourceNotFoundException;
import tn.esprit.mariemchaaabane.repository.IAgenceRepository;
import tn.esprit.mariemchaaabane.repository.IVehiculeRepository;
import tn.esprit.mariemchaaabane.service.IVehiculeService;
import tn.esprit.mariemchaaabane.web.controller.AgenceRestController;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.mariemchaaabane.domain.Equipement;
import tn.esprit.mariemchaaabane.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class VehiculeServiceimpls implements IVehiculeService {
    private final IVehiculeRepository vehiculerepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;
    @Override
    public Vehicule create(Vehicule vehicule) {
        return vehiculerepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculerepository.findById(id).orElseThrow(() -> new RuntimeException("vehicule not found"));
    }

    @Override
    public Vehicule assignAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = findById(idVehicule);
        Agence agence = agenceRepository.findById(idAgence).orElseThrow(() -> new ResourceNotFoundException("Agence" , idAgence));
        vehicule.setAgence(agence);
        return vehiculerepository.save(vehicule);
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculerepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        vehiculerepository.deleteById(id);

    }

    @Override
    public Vehicule update(Vehicule vehicule) {
        return vehiculerepository.save(vehicule);
    }
    @Override
    public  Vehicule assignEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findById(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
        vehicule.getEquipements().add(equipement);
        return vehiculerepository.save(vehicule);
    }

    @Override
    public  Vehicule unassignEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findById(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
        vehicule.getEquipements().remove(equipement);
        return vehiculerepository.save(vehicule);
    }

    @Override
    public Vehicule addAndAssignAgence(Vehicule vehicule, Long idAgence) {
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence));
        vehicule.setAgence(agence);
        return vehiculerepository.save(vehicule);
    }

    }
