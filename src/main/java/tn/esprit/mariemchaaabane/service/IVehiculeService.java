package tn.esprit.mariemchaaabane.service;

import tn.esprit.mariemchaaabane.domain.CategorieVehicule;
import tn.esprit.mariemchaaabane.domain.StatutVehicule;
import tn.esprit.mariemchaaabane.web.DTO.NbVehiculesAgenceDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeRequestDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResponseDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResumeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IVehiculeService {


    VehiculeResponseDTO findById(Long id);
    List<VehiculeResumeDTO> findAll();
    VehiculeResponseDTO create(VehiculeRequestDTO dto);


    List<VehiculeResumeDTO> findByMarque(String marque);
    List<VehiculeResumeDTO> findByCategorieAndStatut(CategorieVehicule categorie, StatutVehicule statut);
    List<VehiculeResumeDTO> findByTarifBetween(BigDecimal min, BigDecimal max);
    List<VehiculeResumeDTO> findByModeleContenant(String motCle);
    List<VehiculeResumeDTO> findByCategories(List<CategorieVehicule> categories);
    boolean existsByImmatriculation(String immatriculation);
    long countByStatut(StatutVehicule statut);
    List<VehiculeResumeDTO> findTop3MoinsChers();
    List<VehiculeResumeDTO> findByAgence(Long agenceId);
    List<VehiculeResumeDTO> findSansAgence();


    List<VehiculeResumeDTO> findByVilleAgence(String ville);
    List<VehiculeResumeDTO> findByEquipement(String libelle);
    List<VehiculeResumeDTO> findAvecAuMoinsNEquipements(long n);
    List<VehiculeResumeDTO> findReservesParClient(Long clientId);
    List<VehiculeResumeDTO> findJamaisReserves();
    List<VehiculeResumeDTO> findEnMaintenanceLe(LocalDate date);

    List<NbVehiculesAgenceDTO> compterParAgence();
}