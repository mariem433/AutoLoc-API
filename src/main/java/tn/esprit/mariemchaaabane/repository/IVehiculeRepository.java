package tn.esprit.mariemchaaabane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.mariemchaaabane.domain.CategorieVehicule;
import tn.esprit.mariemchaaabane.domain.StatutVehicule;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.web.DTO.NbVehiculesAgenceDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByMarque(String marque);
    List<Vehicule> findByCategorieAndStatut(CategorieVehicule categorie, StatutVehicule statut);
    List<Vehicule> findByTarifJournalierBetweenOrderByTarifJournalierAsc(BigDecimal min, BigDecimal max);
    List<Vehicule> findByModeleContainingIgnoreCase(String motCle);
    List<Vehicule> findByCategorieIn(List<CategorieVehicule> categories);
    boolean existsByImmatriculation(String immatriculation);
    long countByStatut(StatutVehicule statut);
    List<Vehicule> findTop3ByOrderByTarifJournalierAsc();
    List<Vehicule> findByAgenceIdAgence(Long agenceId);
    List<Vehicule> findByAgenceIsNull();
    @Query("SELECT v FROM Vehicule v JOIN v.agence a WHERE a.ville = :ville")
    List<Vehicule> findByVilleAgence(@Param("ville") String ville);
    @Query("SELECT DISTINCT v FROM Vehicule v JOIN v.equipements e WHERE e.libelle = :libelle")
    List<Vehicule> findByEquipement(@Param("libelle") String libelle);
    @Query("""
           SELECT v FROM Vehicule v JOIN v.equipements e
           GROUP BY v
           HAVING COUNT(e) >= :n
           """)
    List<Vehicule> findAvecAuMoinsNEquipements(@Param("n") long n);
    @Query("""
           SELECT DISTINCT v FROM Vehicule v
           JOIN v.reservations r
           JOIN r.client c
           WHERE c.idClient = :clientId
           """)
    List<Vehicule> findReservesParClient(@Param("clientId") Long clientId);
    @Query("SELECT v FROM Vehicule v LEFT JOIN v.reservations r WHERE r.idReservation IS NULL")
    List<Vehicule> findJamaisReserves();
    @Query("""
           SELECT DISTINCT v FROM Maintenance m
           JOIN m.vehicule v
           WHERE m.dateDebut <= :date
             AND (m.dateFin IS NULL OR m.dateFin >= :date)
           """)
    List<Vehicule> findEnMaintenanceLe(@Param("date") LocalDate date);
    @Query("""
           SELECT v FROM Vehicule v
           LEFT JOIN FETCH v.agence
           LEFT JOIN FETCH v.equipements
           WHERE v.idVehicule = :id
           """)
    Optional<Vehicule> findByIdAvecEquipements(@Param("id") Long id);
    @Query("""
           SELECT new tn.esprit.mariemchaaabane.web.DTO.NbVehiculesAgenceDTO(a.nom, COUNT(v))
           FROM Agence a LEFT JOIN a.vehicules v
           GROUP BY a.idAgence, a.nom
           """)
    List<NbVehiculesAgenceDTO> compterParAgence();
}