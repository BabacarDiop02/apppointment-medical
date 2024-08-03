package sn.diop.babacar.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sn.diop.babacar.api.dto.MedecinDTO;
import sn.diop.babacar.api.entitie.Medecin;
import sn.diop.babacar.api.mapper.MedecinMapper;
import sn.diop.babacar.api.repository.MedecinRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private MedecinMapper medecinMapper;

    private static final String UPLOAD_DIR = "/src/main/resources/images/doctors/";

    public Set<MedecinDTO> getAllMedecin() {
        List<Medecin> medecins = medecinRepository.findAll();
        return medecins.stream().map(medecinMapper::toDto).collect(Collectors.toSet());
    }

    public MedecinDTO getMedecinById(Long id) {
        return medecinMapper.toDto(medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("Medecin not found")));
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public void deleteMedecin(Medecin medecin) {
        medecinRepository.delete(medecin);
    }

    public Set<MedecinDTO> getMedecinBySpecialty(String specialty) {
        List<Medecin> medecins = medecinRepository.findBySpecialty(specialty);
        return medecins.stream().map(medecinMapper::toDto).collect(Collectors.toSet());
    }

    public ResponseEntity<MedecinDTO> updateMedecin(Long id, String firstName, String lastName, String mail, String phone, String address, String biography, String specialty, MultipartFile image) {
        try {
            MedecinDTO medecinDTO = this.getMedecinById(id);
            Medecin medecin = medecinMapper.toEntity(medecinDTO);
            if (image != null && !image.isEmpty()) {
                //Validation du fichier image
                String filename = StringUtils.cleanPath(image.getOriginalFilename());
                if (!filename.matches(".*\\.(jpg|jpeg|png)$")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }

                // Supprimer l'ancienne image
                Path oldImagePath = Paths.get(UPLOAD_DIR + medecin.getImage());
                Files.deleteIfExists(oldImagePath);

                // Enregistrer le fichier sur le serveur
                Path uploadpath = Paths.get(UPLOAD_DIR + filename);
                Files.copy(image.getInputStream(), uploadpath, StandardCopyOption.REPLACE_EXISTING);

                medecin.setImage(filename);
            }

            // Mettre à jour les autre champs
            if (firstName != null) medecin.setFirstName(firstName);
            if (lastName != null) medecin.setLastName(lastName);
            if (mail != null) medecin.setMail(mail);
            if (phone != null) medecin.setPhone(phone);
            if (address != null) medecin.setAddress(address);
            if (biography != null) medecin.setBiography(biography);
            if (specialty != null) medecin.setSpecialty(specialty);

            Medecin updatedMedecin = this.saveMedecin(medecin);
            return ResponseEntity.status(HttpStatus.OK).body(medecinMapper.toDto(updatedMedecin));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<MedecinDTO> createMedecin(String firstName, String lastName, String mail, String phone, String address, String biography, String specialty, MultipartFile image) {
        try {
            // Validation du fichier
            if (image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            String filename = StringUtils.cleanPath(image.getOriginalFilename());
            if (!filename.matches(".*\\.(jpg|jpeg|png)$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            // Enregistrer le fichier sur le serveur
            Path uploadpath = Paths.get(UPLOAD_DIR + filename);
            Files.copy(image.getInputStream(), uploadpath, StandardCopyOption.REPLACE_EXISTING);

            // Creation de l'entité Medecin
            MedecinDTO medecinDTO = MedecinDTO.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .mail(mail)
                    .phone(phone)
                    .address(address)
                    .biography(biography)
                    .specialty(specialty)
                    .image(filename)
                    .build();
            Medecin medecin = medecinMapper.toEntity(medecinDTO);
            Medecin savedMedecin = this.saveMedecin(medecin);
            return ResponseEntity.status(HttpStatus.CREATED).body(medecinMapper.toDto(savedMedecin));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
