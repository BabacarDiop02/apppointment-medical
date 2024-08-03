package sn.diop.babacar.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sn.diop.babacar.api.dto.AppointmentDTO;
import sn.diop.babacar.api.entitie.Appointment;
import sn.diop.babacar.api.entitie.StatusType;
import sn.diop.babacar.api.mapper.AppointmentMapper;
import sn.diop.babacar.api.repository.AppointmentRepository;
import sn.diop.babacar.api.repository.MedecinRepository;
import sn.diop.babacar.api.repository.PatientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<Appointment> createAppointment(String objective, String description, LocalDateTime dateTime,  Long patientId, Long medecinId) {
        LocalDate date = dateTime.toLocalDate();
        LocalTime hour = dateTime.toLocalTime();

        Appointment appointment = Appointment.builder()
                .objective(objective)
                .description(description)
                .date(date)
                .hour(hour)
                .status(StatusType.PENDING)
                .medecin(medecinRepository.findById(medecinId).orElseThrow(() -> new RuntimeException("Medecin not found")))
                .patient(patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found")))
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }

    public Set<AppointmentDTO> getAllAppointmentsByPatient(Long patientId) {
        Set<Appointment> appointments = appointmentRepository.findByPatient(patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found")));
        return appointments.stream().map(appointmentMapper::toDto).collect(Collectors.toSet());
    }
}
