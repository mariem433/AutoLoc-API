package tn.esprit.mariemchaaabane.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mariemchaaabane.domain.CategorieVehicule;
import tn.esprit.mariemchaaabane.domain.StatutVehicule;
import tn.esprit.mariemchaaabane.service.IVehiculeService;
import tn.esprit.mariemchaaabane.web.DTO.NbVehiculesAgenceDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeRequestDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResponseDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResumeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @GetMapping
    public List<VehiculeResumeDTO> findAll() {
        return vehiculeService.findAll();
    }

    @GetMapping("/{id}")
    public VehiculeResponseDTO findById(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @PostMapping
    public ResponseEntity<VehiculeResponseDTO> create(@Valid @RequestBody VehiculeRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.create(dto));
    }



    @GetMapping("/recherche/marque/{marque}")
    public List<VehiculeResumeDTO> findByMarque(@PathVariable String marque) {
        return vehiculeService.findByMarque(marque);
    }

    @GetMapping("/recherche/categorie/{categorie}/statut/{statut}")
    public List<VehiculeResumeDTO> findByCategorieAndStatut(@PathVariable CategorieVehicule categorie,
                                                            @PathVariable StatutVehicule statut) {
        return vehiculeService.findByCategorieAndStatut(categorie, statut);
    }

    @GetMapping("/recherche/tarif/{min}/{max}")
    public List<VehiculeResumeDTO> findByTarifBetween(@PathVariable BigDecimal min, @PathVariable BigDecimal max) {
        return vehiculeService.findByTarifBetween(min, max);
    }

    @GetMapping("/recherche/modele/{motCle}")
    public List<VehiculeResumeDTO> findByModeleContenant(@PathVariable String motCle) {
        return vehiculeService.findByModeleContenant(motCle);
    }

    @GetMapping("/recherche/categories/{categories}")
    public List<VehiculeResumeDTO> findByCategories(@PathVariable List<CategorieVehicule> categories) {
        return vehiculeService.findByCategories(categories);
    }

    @GetMapping("/recherche/existe/{immatriculation}")
    public boolean existsByImmatriculation(@PathVariable String immatriculation) {
        return vehiculeService.existsByImmatriculation(immatriculation);
    }

    @GetMapping("/recherche/nombre/statut/{statut}")
    public long countByStatut(@PathVariable StatutVehicule statut) {
        return vehiculeService.countByStatut(statut);
    }

    @GetMapping("/recherche/moins-chers")
    public List<VehiculeResumeDTO> findTop3MoinsChers() {
        return vehiculeService.findTop3MoinsChers();
    }

    @GetMapping("/recherche/agence/{agenceId}")
    public List<VehiculeResumeDTO> findByAgence(@PathVariable Long agenceId) {
        return vehiculeService.findByAgence(agenceId);
    }

    @GetMapping("/recherche/sans-agence")
    public List<VehiculeResumeDTO> findSansAgence() {
        return vehiculeService.findSansAgence();
    }

    @GetMapping("/ville/{ville}")
    public List<VehiculeResumeDTO> findByVilleAgence(@PathVariable String ville) {
        return vehiculeService.findByVilleAgence(ville);
    }

    @GetMapping("/equipement/{libelle}")
    public List<VehiculeResumeDTO> findByEquipement(@PathVariable String libelle) {
        return vehiculeService.findByEquipement(libelle);
    }

    @GetMapping("/equipements/min/{n}")
    public List<VehiculeResumeDTO> findAvecAuMoinsNEquipements(@PathVariable long n) {
        return vehiculeService.findAvecAuMoinsNEquipements(n);
    }

    @GetMapping("/client/{clientId}")
    public List<VehiculeResumeDTO> findReservesParClient(@PathVariable Long clientId) {
        return vehiculeService.findReservesParClient(clientId);
    }

    @GetMapping("/jamais-reserves")
    public List<VehiculeResumeDTO> findJamaisReserves() {
        return vehiculeService.findJamaisReserves();
    }

    @GetMapping("/maintenance/{date}")
    public List<VehiculeResumeDTO> findEnMaintenanceLe(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return vehiculeService.findEnMaintenanceLe(date);
    }

    @GetMapping("/stats/par-agence")
    public List<NbVehiculesAgenceDTO> compterParAgence() {
        return vehiculeService.compterParAgence();
    }
}