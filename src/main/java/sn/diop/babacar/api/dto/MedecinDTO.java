package sn.diop.babacar.api.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class MedecinDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String address;
    private String biography;
    private String specialty;
    private String image;
}
