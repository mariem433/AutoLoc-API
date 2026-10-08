package tn.esprit.mariemchaaabane.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.repository.iVehiculeRepository;
import tn.esprit.mariemchaaabane.service.iVehiculeService;

import java.util.List;

@Service
@RequiredArgsConstructor

public class VehiculeServiceimpls implements iVehiculeService {
    private iVehiculeRepository vehiculerepository;
    @Override
    public Vehicule create(Vehicule vehicule) {
        return vehiculerepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculerepository.findById(id).orElseThrow(() -> new RuntimeException("vehicule not found"));
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
}
