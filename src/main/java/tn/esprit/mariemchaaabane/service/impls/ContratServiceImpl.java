package tn.esprit.mariemchaaabane.service.impls;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.mariemchaaabane.domain.Contrat;
import tn.esprit.mariemchaaabane.domain.Paiement;
import tn.esprit.mariemchaaabane.exception.ResourceNotFoundException;
import tn.esprit.mariemchaaabane.repository.IContratRepository;
import tn.esprit.mariemchaaabane.repository.IPaiementRepository;
import tn.esprit.mariemchaaabane.service.IContratService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final IPaiementRepository iPaiementRepository;

    @Override
    public Contrat create(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'identifiant ", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        Contrat existant = findById(id);
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setDateSignature(contrat.getDateSignature());
        existant.setValide(contrat.isValide());
        return contratRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }
    public Contrat addWithPayment(Contrat contart) {
        return null;
    }
    @Override
    @Transactional
    public Contrat addWithPayments(Contrat c) {
        Contrat newcontrat = contratRepository.save(c);
        for (Paiement paiement : newcontrat.getPaiements()) {
            paiement = iPaiementRepository.save(paiement);
            paiement.setContrat(newcontrat);

        }
        return contratRepository.save(c);
    }
}