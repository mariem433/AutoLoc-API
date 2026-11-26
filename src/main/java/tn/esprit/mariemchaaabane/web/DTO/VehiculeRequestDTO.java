package tn.esprit.mariemchaaabane.web.DTO;

import tn.esprit.mariemchaaabane.domain.CategorieVehicule;

import java.math.BigDecimal;

public record VehiculeRequestDTO(

        String immatriculation,
        String marque,
        String modele,
        CategorieVehicule categorie,
        BigDecimal tarifJournalier,

        Long agenceId
) { }