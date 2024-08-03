package sn.diop.babacar.api.mapper;

import org.mapstruct.Mapper;
import sn.diop.babacar.api.dto.AppointmentDTO;
import sn.diop.babacar.api.entitie.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDTO toDto(Appointment appointment);
    Appointment toEntity(AppointmentDTO appointmentDTO);
}
