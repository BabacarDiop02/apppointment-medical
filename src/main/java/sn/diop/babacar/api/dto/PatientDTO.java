package sn.diop.babacar.api.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String mail;
    private String phone;
}
