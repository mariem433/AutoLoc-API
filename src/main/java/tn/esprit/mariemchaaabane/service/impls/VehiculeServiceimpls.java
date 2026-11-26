package tn.esprit.mariemchaaabane.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.mariemchaaabane.domain.Agence;
import tn.esprit.mariemchaaabane.domain.CategorieVehicule;
import tn.esprit.mariemchaaabane.domain.StatutVehicule;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.exception.ImmatriculationDejaUtiliseeException;
import tn.esprit.mariemchaaabane.exception.ResourceNotFoundException;
import tn.esprit.mariemchaaabane.repository.IAgenceRepository;
import tn.esprit.mariemchaaabane.repository.IVehiculeRepository;
import tn.esprit.mariemchaaabane.service.IVehiculeService;
import tn.esprit.mariemchaaabane.web.DTO.NbVehiculesAgenceDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeRequestDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResponseDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResumeDTO;
import tn.esprit.mariemchaaabane.web.mapper.VehiculeMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceimpls implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;



    @Override
    @Transactional(readOnly = true)
    public VehiculeResponseDTO findById(Long id) {

        Vehicule vehicule = vehiculeRepository.findByIdAvecEquipements(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
        return vehiculeMapper.toDto(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findAll() {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findAll());
    }

    @Override
    @Transactional
    public VehiculeResponseDTO create(VehiculeRequestDTO dto) {

        if (vehiculeRepository.existsByImmatriculation(dto.immatriculation())) {
            throw new ImmatriculationDejaUtiliseeException(dto.immatriculation());
        }


        Vehicule vehicule = vehiculeMapper.toEntity(dto);


        Agence agence = agenceRepository.findById(dto.agenceId())
                .orElseThrow(() -> new ResourceNotFoundException("Agence", dto.agenceId()));
        vehicule.setAgence(agence);
        agence.getVehicules().add(vehicule);
        return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
    }
    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByMarque(String marque) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByMarque(marque));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByCategorieAndStatut(CategorieVehicule categorie, StatutVehicule statut) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByCategorieAndStatut(categorie, statut));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByTarifBetween(BigDecimal min, BigDecimal max) {
        return vehiculeMapper.toResumeDtoList(
                vehiculeRepository.findByTarifJournalierBetweenOrderByTarifJournalierAsc(min, max));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByModeleContenant(String motCle) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByModeleContainingIgnoreCase(motCle));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByCategories(List<CategorieVehicule> categories) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByCategorieIn(categories));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByImmatriculation(String immatriculation) {
        return vehiculeRepository.existsByImmatriculation(immatriculation);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByStatut(StatutVehicule statut) {
        return vehiculeRepository.countByStatut(statut);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findTop3MoinsChers() {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findTop3ByOrderByTarifJournalierAsc());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByAgence(Long agenceId) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByAgenceIdAgence(agenceId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findSansAgence() {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByAgenceIsNull());
    }



    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByVilleAgence(String ville) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByVilleAgence(ville));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findByEquipement(String libelle) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findByEquipement(libelle));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findAvecAuMoinsNEquipements(long n) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findAvecAuMoinsNEquipements(n));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findReservesParClient(Long clientId) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findReservesParClient(clientId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findJamaisReserves() {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findJamaisReserves());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResumeDTO> findEnMaintenanceLe(LocalDate date) {
        return vehiculeMapper.toResumeDtoList(vehiculeRepository.findEnMaintenanceLe(date));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbVehiculesAgenceDTO> compterParAgence() {
        return vehiculeRepository.compterParAgence();
    }
}