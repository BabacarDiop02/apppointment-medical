package sn.diop.babacar.api.entitie;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "medecins")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medecin_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "biography")
    private String biography;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "medecin",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;
}
