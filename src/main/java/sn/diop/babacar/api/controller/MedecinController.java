package sn.diop.babacar.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.diop.babacar.api.dto.MedecinDTO;
import sn.diop.babacar.api.entitie.Medecin;
import sn.diop.babacar.api.service.MedecinService;

import java.util.Set;

@RestController
@RequestMapping(path = "api/medecins")
@CrossOrigin("*")
public class MedecinController {
    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public Set<MedecinDTO> getAllMedecin() {
        return medecinService.getAllMedecin();
    }

    @GetMapping("/{id}")
    public MedecinDTO getMedecinById(@PathVariable("id") Long id) {
        return medecinService.getMedecinById(id);
    }

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MedecinDTO> createMedecin(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String mail,
                                 @RequestParam String phone,
                                 @RequestParam String address,
                                 @RequestParam String biography,
                                 @RequestParam String specialty,
                                 @RequestParam MultipartFile image) {
        return medecinService.createMedecin(firstName, lastName, mail, phone, address, biography, specialty, image);
    }

    @PutMapping(path = "update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MedecinDTO> updateMedecin(@PathVariable("id") Long id,
                                                 @RequestParam(required = false) String firstName,
                                                 @RequestParam(required = false) String lastName,
                                                 @RequestParam(required = false) String mail,
                                                 @RequestParam(required = false) String phone,
                                                 @RequestParam(required = false) String address,
                                                 @RequestParam(required = false) String biography,
                                                 @RequestParam(required = false) String specialty,
                                                 @RequestParam(required = false) MultipartFile image) {
        return medecinService.updateMedecin(id, firstName, lastName, mail, phone, address, biography, specialty, image);
    }

    @GetMapping(path = "/specialty/{specialty}")
    public Set<MedecinDTO> getMedecinBySpecialty(@PathVariable String specialty) {
        return medecinService.getMedecinBySpecialty(specialty);
    }

    @DeleteMapping
    public void deleteMedecin(@RequestBody Medecin medecin) {
        medecinService.deleteMedecin(medecin);
    }
}
