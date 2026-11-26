package tn.esprit.mariemchaaabane.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.mariemchaaabane.domain.Equipement;
import tn.esprit.mariemchaaabane.domain.Vehicule;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeRequestDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResponseDTO;
import tn.esprit.mariemchaaabane.web.DTO.VehiculeResumeDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {


    @Mapping(target = "id", source = "idVehicule")
    @Mapping(target = "nomAgence", source = "agence.nom")
    @Mapping(target = "equipements", source = "equipements")
    VehiculeResponseDTO toDto(Vehicule vehicule);


    @Mapping(target = "id", source = "idVehicule")
    VehiculeResumeDTO toResumeDto(Vehicule vehicule);

    List<VehiculeResumeDTO> toResumeDtoList(List<Vehicule> vehicules);


    @Mapping(target = "idVehicule", ignore = true)
    @Mapping(target = "statut", constant = "DISPONIBLE")
    @Mapping(target = "agence", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Vehicule toEntity(VehiculeRequestDTO dto);

    default String toLibelle(Equipement equipement) {
        return equipement.getLibelle();
    }
}