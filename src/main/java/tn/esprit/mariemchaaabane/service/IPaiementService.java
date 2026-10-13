package tn.esprit.mariemchaaabane.service;

import tn.esprit.mariemchaaabane.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement findById(Long id);
    List<Paiement> findAll();
}
