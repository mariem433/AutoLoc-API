package tn.esprit.mariemchaaabane.web.DTO;

import lombok.*;
import tn.esprit.mariemchaaabane.domain.CategorieVehicule;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {
    private String imatriculation;
    private String marque;
    private String modele;
    private CategorieVehicule categorie;
    private BigDecimal tarifJournalier;
    private String nomAgence;


}
