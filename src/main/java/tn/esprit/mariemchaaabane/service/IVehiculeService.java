package tn.esprit.mariemchaaabane.service;

import tn.esprit.mariemchaaabane.domain.Vehicule;

import java.util.List;


public interface IVehiculeService {

    Vehicule create(Vehicule behicule);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
    void deleteById(Long id);
    Vehicule update(Vehicule vehicule);
}
