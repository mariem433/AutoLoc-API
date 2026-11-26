package tn.esprit.mariemchaaabane.web.DTO;

import tn.esprit.mariemchaaabane.domain.CategorieVehicule;
import tn.esprit.mariemchaaabane.domain.StatutVehicule;

import java.math.BigDecimal;
import java.util.List;

public record VehiculeResponseDTO(
        Long id,
        String immatriculation,
        String marque,
        String modele,
        CategorieVehicule categorie,
        BigDecimal tarifJournalier,
        StatutVehicule statut,
        String nomAgence,
        List<String> equipements
) { }