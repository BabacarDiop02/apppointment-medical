package sn.diop.babacar.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.diop.babacar.api.dto.PatientDTO;
import sn.diop.babacar.api.entitie.Patient;
import sn.diop.babacar.api.service.PatientService;

import java.util.Set;

@RestController
@RequestMapping(path = "api/patients")
@CrossOrigin("*")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public Set<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @DeleteMapping
    public void deletePatient(@RequestBody Patient patient) {
        patientService.deletePatient(patient);
    }
}
