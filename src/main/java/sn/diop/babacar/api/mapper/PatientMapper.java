package sn.diop.babacar.api.mapper;

import org.mapstruct.Mapper;
import sn.diop.babacar.api.dto.PatientDTO;
import sn.diop.babacar.api.entitie.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDto(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
}
