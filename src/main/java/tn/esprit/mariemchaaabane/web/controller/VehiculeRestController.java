package tn.esprit.mariemchaaabane.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.service.IVehiculeService;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeDTO;

import java.util.List;

@Tag(name = "Vehicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Lister les véhicules")
    @ApiResponse(responseCode = "200", description = "Liste des véhicules")
    @GetMapping
    public List<VehiculeDTO> findAll() { return vehiculeService.findAll(); }

    @Operation(summary = "Consulter un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable") })
    @GetMapping("/{id}")
    public VehiculeDTO findById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        return vehiculeService.toDTO(vehiculeService.findById(id));
    }

    @Operation(summary = "Créer un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Véhicule créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO create(@RequestBody Vehicule vehicule) { return vehiculeService.toDTO(vehiculeService.create(vehicule));
    }

    @Operation(summary = "Modifier un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule modifié"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides") })
    @PutMapping("/{id}")
    public VehiculeDTO update(@Parameter(description = "Identifiant") @PathVariable Long id,
                              @RequestBody Vehicule vehicule) {
        return vehiculeService.toDTO(vehiculeService.update( vehicule));
    }

    @Operation(summary = "Supprimer un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Véhicule supprimé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable") })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant") @PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @PutMapping("{idVehicule}/{idAgence}")
    public VehiculeDTO assignAgence(@PathVariable Long idVehicule , @PathVariable Long idAgence ) {
        return vehiculeService.toDTO(vehiculeService.assignAgence(idVehicule, idAgence));
    }
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public VehiculeDTO assignEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.toDTO(vehiculeService.assignEquipement(idVehicule, idEquipement));
    }

    @DeleteMapping("/{idVehicule}/equipements/{idEquipement}")
    public VehiculeDTO unassignEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.toDTO(vehiculeService.unassignEquipement(idVehicule, idEquipement));
    }

    @PostMapping("/agence/{idAgence}")
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO addAndAssignAgence(@RequestBody Vehicule vehicule, @PathVariable Long idAgence) {
        return vehiculeService.toDTO(vehiculeService.addAndAssignAgence(vehicule, idAgence));
    }


}