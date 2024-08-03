package sn.diop.babacar.api.mapper;

import org.mapstruct.Mapper;
import sn.diop.babacar.api.dto.MedecinDTO;
import sn.diop.babacar.api.entitie.Medecin;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDTO toDto(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
}
