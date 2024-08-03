package sn.diop.babacar.api.dto;

import lombok.*;
import sn.diop.babacar.api.entitie.StatusType;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class AppointmentDTO {
    private Long id;
    private String objective;
    private String description;
    private LocalDate date;
    private LocalTime hour;
    private StatusType status;
    private Long patientId;
    private Long medecinId;
}
