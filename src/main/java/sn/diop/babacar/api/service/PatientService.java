package sn.diop.babacar.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.diop.babacar.api.dto.PatientDTO;
import sn.diop.babacar.api.entitie.Patient;
import sn.diop.babacar.api.mapper.PatientMapper;
import sn.diop.babacar.api.repository.PatientRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    public Set<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toDto).collect(Collectors.toSet());
    }

    public PatientDTO getPatientById(Long id) {
        return patientMapper.toDto(patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found")));
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }
}
